package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Requirement entity. @author MyEclipse Persistence Tools
 */

public class Requirement implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hos;
	private String hprocessor;
	private Integer hmemory;
	private String hgraphics;
	private String hhardDrive;
	private String hsoundCard;
	private String los;
	private String lprocessor;
	private Integer lmemory;
	private String lgraphics;
	private String lhardDrive;
	private String lsoundCard;
	private Set games = new HashSet(0);

	// Constructors

	/** default constructor */
	public Requirement() {
	}

	/** full constructor */
	public Requirement(String hos, String hprocessor, Integer hmemory,
			String hgraphics, String hhardDrive, String hsoundCard, String los,
			String lprocessor, Integer lmemory, String lgraphics,
			String lhardDrive, String lsoundCard, Set games) {
		this.hos = hos;
		this.hprocessor = hprocessor;
		this.hmemory = hmemory;
		this.hgraphics = hgraphics;
		this.hhardDrive = hhardDrive;
		this.hsoundCard = hsoundCard;
		this.los = los;
		this.lprocessor = lprocessor;
		this.lmemory = lmemory;
		this.lgraphics = lgraphics;
		this.lhardDrive = lhardDrive;
		this.lsoundCard = lsoundCard;
		this.games = games;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHos() {
		return this.hos;
	}

	public void setHos(String hos) {
		this.hos = hos;
	}

	public String getHprocessor() {
		return this.hprocessor;
	}

	public void setHprocessor(String hprocessor) {
		this.hprocessor = hprocessor;
	}

	public Integer getHmemory() {
		return this.hmemory;
	}

	public void setHmemory(Integer hmemory) {
		this.hmemory = hmemory;
	}

	public String getHgraphics() {
		return this.hgraphics;
	}

	public void setHgraphics(String hgraphics) {
		this.hgraphics = hgraphics;
	}

	public String getHhardDrive() {
		return this.hhardDrive;
	}

	public void setHhardDrive(String hhardDrive) {
		this.hhardDrive = hhardDrive;
	}

	public String getHsoundCard() {
		return this.hsoundCard;
	}

	public void setHsoundCard(String hsoundCard) {
		this.hsoundCard = hsoundCard;
	}

	public String getLos() {
		return this.los;
	}

	public void setLos(String los) {
		this.los = los;
	}

	public String getLprocessor() {
		return this.lprocessor;
	}

	public void setLprocessor(String lprocessor) {
		this.lprocessor = lprocessor;
	}

	public Integer getLmemory() {
		return this.lmemory;
	}

	public void setLmemory(Integer lmemory) {
		this.lmemory = lmemory;
	}

	public String getLgraphics() {
		return this.lgraphics;
	}

	public void setLgraphics(String lgraphics) {
		this.lgraphics = lgraphics;
	}

	public String getLhardDrive() {
		return this.lhardDrive;
	}

	public void setLhardDrive(String lhardDrive) {
		this.lhardDrive = lhardDrive;
	}

	public String getLsoundCard() {
		return this.lsoundCard;
	}

	public void setLsoundCard(String lsoundCard) {
		this.lsoundCard = lsoundCard;
	}

	public Set getGames() {
		return this.games;
	}

	public void setGames(Set games) {
		this.games = games;
	}

}