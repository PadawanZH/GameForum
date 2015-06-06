package dao;

/**
 * Favourites entity. @author MyEclipse Persistence Tools
 */

public class Favourites implements java.io.Serializable {

	// Fields

	private Integer id;
	private Guser guser;
	private Post post;

	// Constructors

	/** default constructor */
	public Favourites() {
	}

	/** full constructor */
	public Favourites(Guser guser, Post post) {
		this.guser = guser;
		this.post = post;
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

}