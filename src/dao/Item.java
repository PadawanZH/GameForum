package dao;

import java.sql.Timestamp;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer price;
	private Integer remain;
	private Timestamp closetime;

	// Constructors

	/** default constructor */
	public Item() {
	}

	/** full constructor */
	public Item(String name, Integer price, Integer remain, Timestamp closetime) {
		this.name = name;
		this.price = price;
		this.remain = remain;
		this.closetime = closetime;
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

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRemain() {
		return this.remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	public Timestamp getClosetime() {
		return this.closetime;
	}

	public void setClosetime(Timestamp closetime) {
		this.closetime = closetime;
	}

}