package dao;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post implements java.io.Serializable {

	// Fields

	private Integer id;
	private Section section;
	private Guser guser;
	private String title;
	private String type;
	private Integer shareNum;
	private Integer favouriteNum;
	private Timestamp postTime;
	private String contents;
	private Set replies = new HashSet(0);
	private Set favouriteses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** full constructor */
	public Post(Section section, Guser guser, String title, String type,
			Integer shareNum, Integer favouriteNum, Timestamp postTime,
			String contents, Set replies, Set favouriteses) {
		this.section = section;
		this.guser = guser;
		this.title = title;
		this.type = type;
		this.shareNum = shareNum;
		this.favouriteNum = favouriteNum;
		this.postTime = postTime;
		this.contents = contents;
		this.replies = replies;
		this.favouriteses = favouriteses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Guser getGuser() {
		return this.guser;
	}

	public void setGuser(Guser guser) {
		this.guser = guser;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getShareNum() {
		return this.shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Integer getFavouriteNum() {
		return this.favouriteNum;
	}

	public void setFavouriteNum(Integer favouriteNum) {
		this.favouriteNum = favouriteNum;
	}

	public Timestamp getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

}