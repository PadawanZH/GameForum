package dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Guser entity. @author MyEclipse Persistence Tools
 */

public class Guser implements java.io.Serializable {

	// Fields

	private String account;
	private Usergroup usergroup;
	private String name;
	private String nickName;
	private String passwd;
	private String gender;
	private Date birthday;
	private String email;
	private Integer points;
	private Integer postNum;
	private Integer replyNum;
	private String signature;
	private Integer coinNum;
	private String portraitAddr;
	private Set sections = new HashSet(0);
	private Set followsForFromId = new HashSet(0);
	private Set followsForTargetId = new HashSet(0);
	private Set posts = new HashSet(0);
	private Set replies = new HashSet(0);
	private Set favouriteses = new HashSet(0);
	private Set games = new HashSet(0);
	private Set messagesForReceiver = new HashSet(0);
	private Set messagesForSender = new HashSet(0);

	// Constructors

	/** default constructor */
	public Guser() {
	}

	/** minimal constructor */
	public Guser(String account) {
		this.account = account;
	}

	/** full constructor */
	public Guser(String account, Usergroup usergroup, String name,
			String nickName, String passwd, String gender, Date birthday,
			String email, Integer points, Integer postNum, Integer replyNum,
			String signature, Integer coinNum, String portraitAddr,
			Set sections, Set followsForFromId, Set followsForTargetId,
			Set posts, Set replies, Set favouriteses, Set games,
			Set messagesForReceiver, Set messagesForSender) {
		this.account = account;
		this.usergroup = usergroup;
		this.name = name;
		this.nickName = nickName;
		this.passwd = passwd;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.points = points;
		this.postNum = postNum;
		this.replyNum = replyNum;
		this.signature = signature;
		this.coinNum = coinNum;
		this.portraitAddr = portraitAddr;
		this.sections = sections;
		this.followsForFromId = followsForFromId;
		this.followsForTargetId = followsForTargetId;
		this.posts = posts;
		this.replies = replies;
		this.favouriteses = favouriteses;
		this.games = games;
		this.messagesForReceiver = messagesForReceiver;
		this.messagesForSender = messagesForSender;
	}

	// Property accessors

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getPortraitAddr() {
		return this.portraitAddr;
	}

	public void setPortraitAddr(String portraitAddr) {
		this.portraitAddr = portraitAddr;
	}

	public Set getSections() {
		return this.sections;
	}

	public void setSections(Set sections) {
		this.sections = sections;
	}

	public Set getFollowsForFromId() {
		return this.followsForFromId;
	}

	public void setFollowsForFromId(Set followsForFromId) {
		this.followsForFromId = followsForFromId;
	}

	public Set getFollowsForTargetId() {
		return this.followsForTargetId;
	}

	public void setFollowsForTargetId(Set followsForTargetId) {
		this.followsForTargetId = followsForTargetId;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

	public Set getFavouriteses() {
		return this.favouriteses;
	}

	public void setFavouriteses(Set favouriteses) {
		this.favouriteses = favouriteses;
	}

	public Set getGames() {
		return this.games;
	}

	public void setGames(Set games) {
		this.games = games;
	}

	public Set getMessagesForReceiver() {
		return this.messagesForReceiver;
	}

	public void setMessagesForReceiver(Set messagesForReceiver) {
		this.messagesForReceiver = messagesForReceiver;
	}

	public Set getMessagesForSender() {
		return this.messagesForSender;
	}

	public void setMessagesForSender(Set messagesForSender) {
		this.messagesForSender = messagesForSender;
	}

}