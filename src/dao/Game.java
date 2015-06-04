package dao;

import java.util.Date;

/**
 * Game entity. @author MyEclipse Persistence Tools
 */

public class Game implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer studioId;
	private String platform;
	private String type;
	private String logoAddr;
	private Date releaseDate;
	private Integer requirementId;
	private Integer sectionId;
	private String ownerId;

	// Constructors

	/** default constructor */
	public Game() {
	}

	/** full constructor */
	public Game(String name, Integer studioId, String platform, String type,
			String logoAddr, Date releaseDate, Integer requirementId,
			Integer sectionId, String ownerId) {
		this.name = name;
		this.studioId = studioId;
		this.platform = platform;
		this.type = type;
		this.logoAddr = logoAddr;
		this.releaseDate = releaseDate;
		this.requirementId = requirementId;
		this.sectionId = sectionId;
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

	public Integer getRequirementId() {
		return this.requirementId;
	}

	public void setRequirementId(Integer requirementId) {
		this.requirementId = requirementId;
	}

	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}