package dao;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private Integer stuId;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(Integer stuId) {
		this.stuId = stuId;
	}

	// Property accessors

	public Integer getStuId() {
		return this.stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

}