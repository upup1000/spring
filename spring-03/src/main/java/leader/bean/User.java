package leader.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * 用户的状态数据类
 * @author zss
 */
@Component
@Scope("prototype")
@Lazy
public class User implements Serializable{
	static AtomicInteger count =new AtomicInteger(0);
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private String password;
	private boolean enabled;
	private Date regDate;
    @PostConstruct
	public void init()
	{
    	count.incrementAndGet();
		System.out.println("创建了"+count+"用户");
	}
    @PreDestroy
    public void destory()
    {
    	count.decrementAndGet();
    	System.out.println("剩余"+count+"用户");
    }
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
