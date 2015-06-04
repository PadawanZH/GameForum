package dao;

import java.util.Date;

/**
 * Guser entity. @author MyEclipse Persistence Tools
 */

public class Guser implements java.io.Serializable {

	// Fields

	private String account;
	private String name;
	private String passwd;
	private String gender;
	private Date birthday;
	private String email;
	private Integer groupId;
	private Integer points;
	private Integer postNum;
	private Integer replyNum;
	private String signature;
	private Integer coinNum;

	// Constructors

	/** default constructor */
	public Guser() {
	}

	/** minimal constructor */
	public Guser(String account) {
		this.account = account;
	}

	/** full constructor */
	public Guser(String account, String name, String passwd, String gender,
			Date birthday, String email, Integer groupId, Integer points,
			Integer postNum, Integer replyNum, String signature, Integer coinNum) {
		this.account = account;
		this.name = name;
		this.passwd = passwd;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.groupId = groupId;
		this.points = points;
		this.postNum = postNum;
		this.replyNum = replyNum;
		this.signature = signature;
		this.coinNum = coinNum;
	}

	// Property accessors

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getPostNum() {
		return this.postNum;
	}

	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}

	public Integer getReplyNum() {
		return this.replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getCoinNum() {
		return this.coinNum;
	}

	public void setCoinNum(Integer coinNum) {
		this.coinNum = coinNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Guser [account=" + account + ", name=" + name + ", passwd="
				+ passwd + ", gender=" + gender + ", birthday=" + birthday
				+ ", email=" + email + ", groupId=" + groupId + ", points="
				+ points + ", postNum=" + postNum + ", replyNum=" + replyNum
				+ ", signature=" + signature + ", coinNum=" + coinNum + "]";
	}

}