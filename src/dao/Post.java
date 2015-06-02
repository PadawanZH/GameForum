package dao;

import java.sql.Timestamp;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String authorId;
	private String type;
	private Integer shareNum;
	private Integer favouriteNum;
	private Timestamp postTime;
	private Integer sectionId;
	private String contents;

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** full constructor */
	public Post(String title, String authorId, String type, Integer shareNum,
			Integer favouriteNum, Timestamp postTime, Integer sectionId,
			String contents) {
		this.title = title;
		this.authorId = authorId;
		this.type = type;
		this.shareNum = shareNum;
		this.favouriteNum = favouriteNum;
		this.postTime = postTime;
		this.sectionId = sectionId;
		this.contents = contents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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

	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}