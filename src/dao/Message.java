package dao;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private Guser guserByReceiver;
	private Guser guserBySender;
	private Timestamp time;
	private String marked;
	private String contents;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Guser guserByReceiver, Guser guserBySender, Timestamp time,
			String marked, String contents) {
		this.guserByReceiver = guserByReceiver;
		this.guserBySender = guserBySender;
		this.time = time;
		this.marked = marked;
		this.contents = contents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guser getGuserByReceiver() {
		return this.guserByReceiver;
	}

	public void setGuserByReceiver(Guser guserByReceiver) {
		this.guserByReceiver = guserByReceiver;
	}

	public Guser getGuserBySender() {
		return this.guserBySender;
	}

	public void setGuserBySender(Guser guserBySender) {
		this.guserBySender = guserBySender;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getMarked() {
		return this.marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}