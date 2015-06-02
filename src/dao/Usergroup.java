package dao;

/**
 * Usergroup entity. @author MyEclipse Persistence Tools
 */

public class Usergroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String permissions;

	// Constructors

	/** default constructor */
	public Usergroup() {
	}

	/** full constructor */
	public Usergroup(String name, String permissions) {
		this.name = name;
		this.permissions = permissions;
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

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

}