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
	private Guser guser;
	private String name;
	private Integer studioId;
	private String platform;
	private String type;
	private String logoAddr;
	private Date releaseDate;
	private Set sections = new HashSet(0);

	// Constructors

	/** default constructor */
	public Game() {
	}

	/** full constructor */
	public Game(Requirement requirement, Guser guser, String name,
			Integer studioId, String platform, String type, String logoAddr,
			Date releaseDate, Set sections) {
		this.requirement = requirement;
		this.guser = guser;
		this.name = name;
		this.studioId = studioId;
		this.platform = platform;
		this.type = type;
		this.logoAddr = logoAddr;
		this.releaseDate = releaseDate;
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

	public Guser getGuser() {
		return this.guser;
	}

	public void setGuser(Guser guser) {
		this.guser = guser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStudioId() {
		return this.studioId;
	}

	public void setStudioId(Integer studioId) {
		this.studioId = studioId;
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

	public Set getSections() {
		return this.sections;
	}

	public void setSections(Set sections) {
		this.sections = sections;
	}

}