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

	public void setGuserByTargetId(Guser guserByTargetId) {
		this.guserByTargetId = guserByTargetId;
	}

}