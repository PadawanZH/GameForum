package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Studio entity. @author MyEclipse Persistence Tools
 */

public class Studio implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String location;
	private String description;
	private Set games = new HashSet(0);

	// Constructors

	/** default constructor */
	public Studio() {
	}

	/** full constructor */
	public Studio(String name, String location, String description, Set games) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.games = games;
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

	public Set getGames() {
		return this.games;
	}

	public void setGames(Set games) {
		this.games = games;
	}

}