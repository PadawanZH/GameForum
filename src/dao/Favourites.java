package dao;

/**
 * Favourites entity. @author MyEclipse Persistence Tools
 */

public class Favourites implements java.io.Serializable {

	// Fields

	private Integer id;
	private String belong;
	private String postId;

	// Constructors

	/** default constructor */
	public Favourites() {
	}

	/** full constructor */
	public Favourites(String belong, String postId) {
		this.belong = belong;
		this.postId = postId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

}