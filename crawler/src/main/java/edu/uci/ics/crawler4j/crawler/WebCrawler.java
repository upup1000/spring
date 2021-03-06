/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpStatus;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.exceptions.ContentFetchException;
import edu.uci.ics.crawler4j.crawler.exceptions.PageBiggerThanMaxSizeException;
import edu.uci.ics.crawler4j.crawler.exceptions.ParseException;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.frontier.DocIDServer;
import edu.uci.ics.crawler4j.frontier.Frontier;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.NotAllowedContentException;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * WebCrawler class in the Runnable class that is executed by each crawler thread.
 * 每个爬虫线程执行这里的run方法。
 * @author Yasser Ganjisaffar
 */
public class WebCrawler implements Runnable {

    protected static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);

    /**
     * The id associated to the crawler thread running this instance。
     * 相应的爬虫线程id。
     */
    protected int myId;

    /**
     * The controller instance that has created this crawler thread. This
     * reference to the controller can be used for getting configurations of the
     * current crawl or adding new seeds during runtime.
     * 创建爬虫实例的controller实例。可以从这个指针上获取爬取配置，还可以在运行时添加新的url种子
     */
    protected CrawlController myController;

    /**
     * The thread within which this crawler instance is running.
     */
    private Thread myThread;

    /**
     * The parser that is used by this crawler instance to parse the content of the fetched pages.
     * 页面内容解析器
     */
    private Parser parser;

    /**
     * The fetcher that is used by this crawler instance to fetch the content of pages from the web.
     * 页面内容抓取器
     */
    private PageFetcher pageFetcher;

    /**
     * The RobotstxtServer instance that is used by this crawler instance to
     * determine whether the crawler is allowed to crawl the content of each page.
     * 根据站点robot协议，判定一个页面是否允许抓取
     */
    private RobotstxtServer robotstxtServer;

    /**
     * The DocIDServer that is used by this crawler instance to map each URL to a unique docid.
     * 映射每个url到一个唯一的docid
     */
    private DocIDServer docIdServer;

    /**
     * The Frontier object that manages the crawl queue.
     * 待开发的url管理器
     */
    private Frontier frontier;

    /**
     * Is the current crawler instance waiting for new URLs? This field is
     * mainly used by the controller to detect whether all of the crawler
     * instances are waiting for new URLs and therefore there is no more work
     * and crawling can be stopped.
     * 是否等待新的url。这个变量主要用来决定是不是没有url可爬取，整个爬取工作是不是可以停止了。
     */
    private boolean isWaitingForNewURLs;

    /**
     * Initializes the current instance of the crawler
     *
     * @param id
     *            the id of this crawler instance
     * @param crawlController
     *            the controller that manages this crawling session
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void init(int id, CrawlController crawlController)
        throws InstantiationException, IllegalAccessException {
        this.myId = id;
        this.pageFetcher = crawlController.getPageFetcher();
        this.robotstxtServer = crawlController.getRobotstxtServer();
        this.docIdServer = crawlController.getDocIdServer();
        this.frontier = crawlController.getFrontier();
        this.parser = new Parser(crawlController.getConfig());
        this.myController = crawlController;
        this.isWaitingForNewURLs = false;
    }

    /**
     * Get the id of the current crawler instance
     *
     * @return the id of the current crawler instance
     */
    public int getMyId() {
        return myId;
    }

    public CrawlController getMyController() {
        return myController;
    }

    /**
     * This function is called just before starting the crawl by this crawler
     * instance. It can be used for setting up the data structures or
     * initializations needed by this crawler instance.
     * 在爬取之前调用，用来建立数据结构，或其他必要的初始化
     */
    public void onStart() {
        // Do nothing by default
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called just before the termination of the current
     * crawler instance. It can be used for persisting in-memory data or other
     * finalization tasks.
     * 终止之前，保存内存数据
     */
    public void onBeforeExit() {
        // Do nothing by default
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called once the header of a page is fetched. It can be
     * overridden by sub-classes to perform custom logic for different status
     * codes. For example, 404 pages can be logged, etc.
     * 每次抓取到页面的头部都会调用，子类重写来执行不同状态码的自定义逻辑。比如log一下404的url
     * 
     * @param webUrl WebUrl containing the statusCode
     * @param statusCode Html Status Code number
     * @param statusDescription Html Status COde description
     */
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        // Do nothing by default
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called before processing of the page's URL
     * It can be overridden by subclasses for tweaking of the url before processing it.
     * For example, http://abc.com/def?a=123 - http://abc.com/def
     * 处理页面url之前调用，子类重写可以稍稍变动一下url。比如增加查询参数或者去掉
     * @param curURL current URL which can be tweaked before processing
     * @return tweaked WebURL
     */
    protected WebURL handleUrlBeforeProcess(WebURL curURL) {
        return curURL;
    }

    /**
     * This function is called if the content of a url is bigger than allowed size.
     * 如果页面内容有点过大，怎么版
     * @param urlStr - The URL which it's content is bigger than allowed size
     */
    protected void onPageBiggerThanMaxSize(String urlStr, long pageSize) {
        logger.warn("Skipping a URL: {} which was bigger ( {} ) than max allowed size", urlStr,
                    pageSize);
    }

    /**
     * This function is called if the crawler encounters a page with a 3xx status code
     * 遇到3xx重定向怎么办
     * @param page Partial page object
     */
    protected void onRedirectedStatusCode(Page page) {
        //Subclasses can override this to add their custom functionality
    }

    /**
     * This function is called if the crawler encountered an unexpected http status code ( a
     * status code other than 3xx)
     * 未知状态马怎么办
     * @param urlStr URL in which an unexpected error was encountered while crawling
     * @param statusCode Html StatusCode
     * @param contentType Type of Content
     * @param description Error Description
     */
    protected void onUnexpectedStatusCode(String urlStr, int statusCode, String contentType,
                                          String description) {
        logger.warn("Skipping URL: {}, StatusCode: {}, {}, {}", urlStr, statusCode, contentType,
                    description);
        // Do nothing by default (except basic logging)
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called if the content of a url could not be fetched.
     * url不能抓取怎么办
     * @param webUrl URL which content failed to be fetched
     */
    protected void onContentFetchError(WebURL webUrl) {
        logger.warn("Can't fetch content of: {}", webUrl.getURL());
        // Do nothing by default (except basic logging)
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called when a unhandled exception was encountered during fetching
     * 抓取过程中有未曾捕获的异常怎么办
     * @param webUrl URL where a unhandled exception occured
     */
    protected void onUnhandledException(WebURL webUrl, Throwable e) {
        String urlStr = (webUrl == null ? "NULL" : webUrl.getURL());
        logger.warn("Unhandled exception while fetching {}: {}", urlStr, e.getMessage());
        logger.info("Stacktrace: ", e);
        // Do nothing by default (except basic logging)
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * This function is called if there has been an error in parsing the content.
     * 解析过程中出现错误
     * @param webUrl URL which failed on parsing
     */
    protected void onParseError(WebURL webUrl) {
        logger.warn("Parsing error of: {}", webUrl.getURL());
        // Do nothing by default (Except logging)
        // Sub-classed can override this to add their custom functionality
    }

    /**
     * The CrawlController instance that has created this crawler instance will
     * call this function just before terminating this crawler thread. Classes
     * that extend WebCrawler can override this function to pass their local
     * data to their controller. The controller then puts these local data in a
     * List that can then be used for processing the local data of crawlers (if needed).
     * 爬取终止前CrawlController调用这个函数获取当前爬虫的数据，有必要可以重写并处理controller的数据总和
     * @return currently NULL
     */
    public Object getMyLocalData() {
        return null;
    }

    @Override
    public void run() {
        onStart();
        while (true) {
            List<WebURL> assignedURLs = new ArrayList<>(50);
            isWaitingForNewURLs = true;
            frontier.getNextURLs(50, assignedURLs);
            isWaitingForNewURLs = false;
            
            if (assignedURLs.isEmpty()) {
                if (frontier.isFinished()) {
                    return;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    logger.error("Error occurred", e);
                }
            } else {
                for (WebURL curURL : assignedURLs) {
                    if (myController.isShuttingDown()) {
                        logger.info("Exiting because of controller shutdown.");
                        return;
                    }
                    if (curURL != null) {
                        curURL = handleUrlBeforeProcess(curURL);
                        processPage(curURL);
                        frontier.setProcessed(curURL);
                    }
                }
            }
        }
    }

    /**
     * Classes that extends WebCrawler should overwrite this function to tell the
     * crawler whether the given url should be crawled or not. The following
     * default implementation indicates that all urls should be included in the crawl
     * except those with a nofollow flag.
     * 给定的url是否可以抓取
     * @param url
     *            the url which we are interested to know whether it should be
     *            included in the crawl or not.
     * @param referringPage
     *           The Page in which this url was found.
     * @return if the url should be included in the crawl it returns true,
     *         otherwise false is returned.
     */
    public boolean shouldVisit(Page referringPage, WebURL url) {
        if (myController.getConfig().isRespectNoFollow()) {
            return !((referringPage != null &&
                    referringPage.getContentType() != null &&
                    referringPage.getContentType().contains("html") &&
                    ((HtmlParseData)referringPage.getParseData())
                        .getMetaTagValue("robots")
                        .contains("nofollow")) ||
                    url.getAttribute("rel").contains("nofollow"));
        }

        return true;
    }

    /**
     * Determine whether links found at the given URL should be added to the queue for crawling.
     * By default this method returns true always, but classes that extend WebCrawler can
     * override it in order to implement particular policies about which pages should be
     * mined for outgoing links and which should not.
     * 给定页面的连接是否允许被添加到url队列来爬取
     * If links from the URL are not being followed, then we are not operating as
     * a web crawler and need not check robots.txt before fetching the single URL.
     * (see definition at http://www.robotstxt.org/faq/what.html).  Thus URLs that
     * return false from this method will not be subject to robots.txt filtering.
     * 如果不允许页面的连接被爬取，在抓取这个url前不会检查robots协议
     * @param url the URL of the page under consideration
     * @return true if outgoing links from this page should be added to the queue.
     */
    protected boolean shouldFollowLinksIn(WebURL url) {
        return true;
    }

    /**
     * Classes that extends WebCrawler should overwrite this function to process
     * the content of the fetched and parsed page.
     *
     * @param page
     *            the page object that is just fetched and parsed.
     */
    public void visit(Page page) {
        // Do nothing by default
        // Sub-classed should override this to add their custom functionality
    }

    private void processPage(WebURL curURL) {
        PageFetchResult fetchResult = null;
        try {
            if (curURL == null) {
                return;
            }

            fetchResult = pageFetcher.fetchPage(curURL);
            int statusCode = fetchResult.getStatusCode();
            handlePageStatusCode(curURL, statusCode,
                                 EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode,
                                                                               Locale.ENGLISH));
            // Finds the status reason for all known statuses

            Page page = new Page(curURL);
            page.setFetchResponseHeaders(fetchResult.getResponseHeaders());
            page.setStatusCode(statusCode);
            if (statusCode < 200 ||
                statusCode > 299) { // Not 2XX: 2XX status codes indicate success
                if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY ||
                    statusCode == HttpStatus.SC_MOVED_TEMPORARILY ||
                    statusCode == HttpStatus.SC_MULTIPLE_CHOICES ||
                    statusCode == HttpStatus.SC_SEE_OTHER ||
                    statusCode == HttpStatus.SC_TEMPORARY_REDIRECT ||
                    statusCode == 308) { // is 3xx  todo
                    // follow https://issues.apache.org/jira/browse/HTTPCORE-389

                    page.setRedirect(true);

                    String movedToUrl = fetchResult.getMovedToUrl();
                    if (movedToUrl == null) {
                        logger.warn("Unexpected error, URL: {} is redirected to NOTHING",
                                    curURL);
                        return;
                    }
                    page.setRedirectedToUrl(movedToUrl);
                    onRedirectedStatusCode(page);

                    if (myController.getConfig().isFollowRedirects()) {
                        int newDocId = docIdServer.getDocId(movedToUrl);
                        if (newDocId > 0) {
                            logger.debug("Redirect page: {} is already seen", curURL);
                            return;
                        }

                        WebURL webURL = new WebURL();
                        webURL.setURL(movedToUrl);
                        webURL.setParentDocid(curURL.getParentDocid());
                        webURL.setParentUrl(curURL.getParentUrl());
                        webURL.setDepth(curURL.getDepth());
                        webURL.setDocid(-1);
                        webURL.setAnchor(curURL.getAnchor());
                        if (shouldVisit(page, webURL)) {
                            if (!shouldFollowLinksIn(webURL) || robotstxtServer.allows(webURL)) {
                                webURL.setDocid(docIdServer.getNewDocID(movedToUrl));
                                frontier.schedule(webURL);
                            } else {
                                logger.debug(
                                    "Not visiting: {} as per the server's \"robots.txt\" policy",
                                    webURL.getURL());
                            }
                        } else {
                            logger.debug("Not visiting: {} as per your \"shouldVisit\" policy",
                                         webURL.getURL());
                        }
                    }
                } else { // All other http codes other than 3xx & 200
                    String description =
                        EnglishReasonPhraseCatalog.INSTANCE.getReason(fetchResult.getStatusCode(),
                                                                      Locale.ENGLISH); // Finds
                    // the status reason for all known statuses
                    String contentType = fetchResult.getEntity() == null ? "" :
                                         fetchResult.getEntity().getContentType() == null ? "" :
                                         fetchResult.getEntity().getContentType().getValue();
                    onUnexpectedStatusCode(curURL.getURL(), fetchResult.getStatusCode(),
                                           contentType, description);
                }

            } else { // if status code is 200
                if (!curURL.getURL().equals(fetchResult.getFetchedUrl())) {
                    if (docIdServer.isSeenBefore(fetchResult.getFetchedUrl())) {
                        logger.debug("Redirect page: {} has already been seen", curURL);
                        return;
                    }
                    curURL.setURL(fetchResult.getFetchedUrl());
                    curURL.setDocid(docIdServer.getNewDocID(fetchResult.getFetchedUrl()));
                }

                if (!fetchResult.fetchContent(page,
                                              myController.getConfig().getMaxDownloadSize())) {
                    throw new ContentFetchException();
                }

                if (page.isTruncated()) {
                    logger.warn(
                        "Warning: unknown page size exceeded max-download-size, truncated to: " +
                        "({}), at URL: {}",
                        myController.getConfig().getMaxDownloadSize(), curURL.getURL());
                }

                parser.parse(page, curURL.getURL());

                if (shouldFollowLinksIn(page.getWebURL())) {
                    ParseData parseData = page.getParseData();
                    List<WebURL> toSchedule = new ArrayList<>();
                    int maxCrawlDepth = myController.getConfig().getMaxDepthOfCrawling();
                    for (WebURL webURL : parseData.getOutgoingUrls()) {
                        webURL.setParentDocid(curURL.getDocid());
                        webURL.setParentUrl(curURL.getURL());
                        int newdocid = docIdServer.getDocId(webURL.getURL());
                        if (newdocid > 0) {
                            // This is not the first time that this Url is visited. So, we set the
                            // depth to a negative number.
                            webURL.setDepth((short) -1);
                            webURL.setDocid(newdocid);
                        } else {
                            webURL.setDocid(-1);
                            webURL.setDepth((short) (curURL.getDepth() + 1));
                            if ((maxCrawlDepth == -1) || (curURL.getDepth() < maxCrawlDepth)) {
                                if (shouldVisit(page, webURL)) {
                                    if (robotstxtServer.allows(webURL)) {
                                        webURL.setDocid(docIdServer.getNewDocID(webURL.getURL()));
                                        toSchedule.add(webURL);
                                    } else {
                                        logger.debug(
                                            "Not visiting: {} as per the server's \"robots.txt\" " +
                                            "policy", webURL.getURL());
                                    }
                                } else {
                                    logger.debug(
                                        "Not visiting: {} as per your \"shouldVisit\" policy",
                                        webURL.getURL());
                                }
                            }
                        }
                    }
                    frontier.scheduleAll(toSchedule);
                } else {
                    logger.debug("Not looking for links in page {}, "
                                 + "as per your \"shouldFollowLinksInPage\" policy",
                                 page.getWebURL().getURL());
                }

                boolean noIndex = myController.getConfig().isRespectNoIndex() &&
                    page.getContentType() != null &&
                    page.getContentType().contains("html") &&
                    ((HtmlParseData)page.getParseData())
                        .getMetaTagValue("robots").
                        contains("noindex");

                if (!noIndex) {
                    visit(page);
                }
            }
        } catch (PageBiggerThanMaxSizeException e) {
            onPageBiggerThanMaxSize(curURL.getURL(), e.getPageSize());
        } catch (ParseException pe) {
            onParseError(curURL);
        } catch (ContentFetchException cfe) {
            onContentFetchError(curURL);
        } catch (NotAllowedContentException nace) {
            logger.debug(
                "Skipping: {} as it contains binary content which you configured not to crawl",
                curURL.getURL());
        } catch (Exception e) {
            onUnhandledException(curURL, e);
        } finally {
            if (fetchResult != null) {
                fetchResult.discardContentIfNotConsumed();
            }
        }
    }

    public Thread getThread() {
        return myThread;
    }

    public void setThread(Thread myThread) {
        this.myThread = myThread;
    }

    public boolean isNotWaitingForNewURLs() {
        return !isWaitingForNewURLs;
    }
}
