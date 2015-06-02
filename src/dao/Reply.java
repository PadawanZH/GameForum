package dao;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer postId;
	private Integer floor;
	private String userId;
	private Integer favouriteNum;
	private Timestamp postTime;
	private String contents;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** full constructor */
	public Reply(Integer postId, Integer floor, String userId,
			Integer favouriteNum, Timestamp postTime, String contents) {
		this.postId = postId;
		this.floor = floor;
		this.userId = userId;
		this.favouriteNum = favouriteNum;
		this.postTime = postTime;
		this.contents = contents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostId() {
		return this.postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getFloor() {
		return this.floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}