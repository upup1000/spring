package edu.ldcollege.bean;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.util.DateUtil;

public class HomeWork {
	private int id;
	private int classid;
	private int lessionid;
	private String homeworkfilepath;
	private String homeworkfilename;
	private Integer negativecount;
	private Boolean bestflag;
	private Integer starcount;
	private Integer correctflag;
	private String createdate;
    
	public HomeWork(LdHomeWork homeWork)
	{
		this.id=homeWork.getId();
		this.classid=homeWork.getClassid();
		this.lessionid=homeWork.getLessionid();
		this.homeworkfilepath=homeWork.getHomeworkfilepath();
		this.homeworkfilename=homeWork.getHomeworkfilename();
		this.negativecount=homeWork.getNegativecount();
		this.bestflag=homeWork.getBestflag();
		this.starcount=homeWork.getStarcount();
		this.correctflag=homeWork.getCorrectflag();
		this.createdate=DateUtil.getLogYMDStringDate(homeWork.getCreatedate());
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public int getLessionid() {
		return lessionid;
	}

	public void setLessionid(int lessionid) {
		this.lessionid = lessionid;
	}

	public String getHomeworkfilepath() {
		return homeworkfilepath;
	}

	public void setHomeworkfilepath(String homeworkfilepath) {
		this.homeworkfilepath = homeworkfilepath;
	}

	public String getHomeworkfilename() {
		return homeworkfilename;
	}

	public void setHomeworkfilename(String homeworkfilename) {
		this.homeworkfilename = homeworkfilename;
	}

	public Integer getNegativecount() {
		return negativecount;
	}

	public void setNegativecount(Integer negativecount) {
		this.negativecount = negativecount;
	}

	public Boolean getBestflag() {
		return bestflag;
	}

	public void setBestflag(Boolean bestflag) {
		this.bestflag = bestflag;
	}

	public Integer getStarcount() {
		return starcount;
	}

	public void setStarcount(Integer starcount) {
		this.starcount = starcount;
	}

	public Integer getCorrectflag() {
		return correctflag;
	}

	public void setCorrectflag(Integer correctflag) {
		this.correctflag = correctflag;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}
