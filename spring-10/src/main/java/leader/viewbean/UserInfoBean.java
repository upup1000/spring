package leader.viewbean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInfoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 姓名只能是英文字符和数字，长度为6-18
	 */
	@Size(min = 6, max = 13, message = "{sample.bean_validation.error1}")
	@Pattern(regexp = "^[a-z0-9A-Z]+$", message = "{sample.bean_validation.error1}")
	private String name;
	/**
	 * 注意要校验年龄为18+到99之间，
	 */
	@Min(18)
	@Max(99)
	private int age;

	private String sessionId;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
