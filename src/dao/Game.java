package dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Game entity. @author MyEclipse Persistence Tools
 */

public class Game implements java.io.Serializable {

	// Fields

	private Integer id;
	private Requirement requirement;
	private Studio studio;
	private String name;
	private String platform;
	private String type;
	private String logoAddr;
	private Date releaseDate;
	private String description;
	private Set sections = new HashSet(0);

	// Constructors

	/** default constructor */
	public Game() {
	}

	/** full constructor */
	public Game(Requirement requirement, Studio studio, String name,
			String platform, String type, String logoAddr, Date releaseDate,
			String description, Set sections) {
		this.requirement = requirement;
		this.studio = studio;
		this.name = name;
		this.platform = platform;
		this.type = type;
		this.logoAddr = logoAddr;
		this.releaseDate = releaseDate;
		this.description = description;
		this.sections = sections;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Requirement getRequirement() {
		return this.requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public Studio getStudio() {
		return this.studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogoAddr() {
		return this.logoAddr;
	}

	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getSections() {
		return this.sections;
	}

	public void setSections(Set sections) {
		this.sections = sections;
	}

}