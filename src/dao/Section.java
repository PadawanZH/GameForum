package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Section entity. @author MyEclipse Persistence Tools
 */

public class Section implements java.io.Serializable {

	// Fields

	private Integer id;
	private Guser guser;
	private Game game;
	private String name;
	private Integer postNum;
	private String ownerType;
	private String sectionPictureAddr;
	private Set posts = new HashSet(0);

	// Constructors

	/** default constructor */
	public Section() {
	}

	/** full constructor */
	public Section(Guser guser, Game game, String name, Integer postNum,
			String ownerType, String sectionPictureAddr, Set posts) {
		this.guser = guser;
		this.game = game;
		this.name = name;
		this.postNum = postNum;
		this.ownerType = ownerType;
		this.sectionPictureAddr = sectionPictureAddr;
		this.posts = posts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guser getGuser() {
		return this.guser;
	}

	public void setGuser(Guser guser) {
		this.guser = guser;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
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

	public String getSectionPictureAddr() {
		return this.sectionPictureAddr;
	}

	public void setSectionPictureAddr(String sectionPictureAddr) {
		this.sectionPictureAddr = sectionPictureAddr;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

}