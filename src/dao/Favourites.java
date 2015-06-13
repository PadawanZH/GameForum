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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guser == null) ? 0 : guser.getAccount().hashCode());
		result = prime * result + ((post == null) ? 0 : post.getId().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Favourites){
			Favourites favourites = (Favourites)obj;
			if(this.getGuser().getAccount().equals( favourites.getGuser().getAccount())
				&& this.getPost().getId().equals( favourites.getPost().getId())
					){
				return true;
			}else{
				return false;
			}
		}
		return super.equals(obj);
	}

}