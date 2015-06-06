package dao;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Guser guser;
	private Post post;
	private Integer floor;
	private Integer favouriteNum;
	private Timestamp postTime;
	private String contents;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** full constructor */
	public Reply(Guser guser, Post post, Integer floor, Integer favouriteNum,
			Timestamp postTime, String contents) {
		this.guser = guser;
		this.post = post;
		this.floor = floor;
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

	public Guser getGuser() {
		return this.guser;
	}

	public void setGuser(Guser guser) {
		this.guser = guser;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Integer getFloor() {
		return this.floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
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