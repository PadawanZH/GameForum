package dao;

/**
 * Section entity. @author MyEclipse Persistence Tools
 */

public class Section implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer postNum;
	private String ownerType;
	private String ownerId;

	// Constructors

	/** default constructor */
	public Section() {
	}

	/** full constructor */
	public Section(String name, Integer postNum, String ownerType,
			String ownerId) {
		this.name = name;
		this.postNum = postNum;
		this.ownerType = ownerType;
		this.ownerId = ownerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPostNum() {
		return this.postNum;
	}

	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}

	public String getOwnerType() {
		return this.ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}