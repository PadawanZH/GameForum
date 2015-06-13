package dao;

/**
 * Follow entity. @author MyEclipse Persistence Tools
 */

public class Follow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Guser guserByFromId;
	private Guser guserByTargetId;

	// Constructors

	/** default constructor */
	public Follow() {
	}

	/** full constructor */
	public Follow(Guser guserByFromId, Guser guserByTargetId) {
		this.guserByFromId = guserByFromId;
		this.guserByTargetId = guserByTargetId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guser getGuserByFromId() {
		return this.guserByFromId;
	}

	public void setGuserByFromId(Guser guserByFromId) {
		this.guserByFromId = guserByFromId;
	}

	public Guser getGuserByTargetId() {
		return this.guserByTargetId;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Follow){
			Follow follow = (Follow) obj;
			if(this.getGuserByFromId().getAccount().equals( ((Follow) obj).getGuserByFromId().getAccount() )
					&& this.getGuserByTargetId().getAccount().equals( ((Follow) obj).getGuserByTargetId().getAccount() )
					){
				return true;
			}else{
				return false;
			}
		}
		return super.equals(obj);
	}

	public void setGuserByTargetId(Guser guserByTargetId) {
		this.guserByTargetId = guserByTargetId;
	}

}