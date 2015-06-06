package dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Usergroup entity. @author MyEclipse Persistence Tools
 */

public class Usergroup implements java.io.Serializable {

	// Fields

	private String name;
	private String permissions;
	private Set gusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Usergroup() {
	}

	/** minimal constructor */
	public Usergroup(String name) {
		this.name = name;
	}

	/** full constructor */
	public Usergroup(String name, String permissions, Set gusers) {
		this.name = name;
		this.permissions = permissions;
		this.gusers = gusers;
	}

	// Property accessors

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

	public Set getGusers() {
		return this.gusers;
	}

	public void setGusers(Set gusers) {
		this.gusers = gusers;
	}

}