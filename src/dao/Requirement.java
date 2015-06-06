package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Requirement entity. @author MyEclipse Persistence Tools
 */

public class Requirement implements java.io.Serializable {

	// Fields

	private Integer id;
	private String os;
	private String processor;
	private Integer memory;
	private String graphics;
	private String hardDrive;
	private String soundCard;
	private Set games = new HashSet(0);

	// Constructors

	/** default constructor */
	public Requirement() {
	}

	/** full constructor */
	public Requirement(String os, String processor, Integer memory,
			String graphics, String hardDrive, String soundCard, Set games) {
		this.os = os;
		this.processor = processor;
		this.memory = memory;
		this.graphics = graphics;
		this.hardDrive = hardDrive;
		this.soundCard = soundCard;
		this.games = games;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getProcessor() {
		return this.processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public Integer getMemory() {
		return this.memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public String getGraphics() {
		return this.graphics;
	}

	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}

	public String getHardDrive() {
		return this.hardDrive;
	}

	public void setHardDrive(String hardDrive) {
		this.hardDrive = hardDrive;
	}

	public String getSoundCard() {
		return this.soundCard;
	}

	public void setSoundCard(String soundCard) {
		this.soundCard = soundCard;
	}

	public Set getGames() {
		return this.games;
	}

	public void setGames(Set games) {
		this.games = games;
	}

}