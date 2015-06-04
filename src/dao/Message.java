package dao;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sender;
	private String receiver;
	private Timestamp time;
	private String marked;
	private String contents;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(String sender, String receiver, Timestamp time,
			String marked, String contents) {
		this.sender = sender;
		this.receiver = receiver;
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

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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