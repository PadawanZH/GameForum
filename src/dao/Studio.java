package dao;

/**
 * Studio entity. @author MyEclipse Persistence Tools
 */

public class Studio implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String location;
	private String description;

	// Constructors

	/** default constructor */
	public Studio() {
	}

	/** full constructor */
	public Studio(String name, String location, String description) {
		this.name = name;
		this.location = location;
		this.description = description;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}