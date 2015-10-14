package com.beingjavaguys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SMSEVENT")
public class XEventSMSModel implements Serializable {
	
	public XEventSMSModel(){
		super();
	}
	
	@Id
	@GeneratedValue
	@Column(name = "messageId")
	private long messageId;
	
	@Column(name = "fromMobNo")
	private String fromMobileNumber;
	
	@Column(name = "toMobNo")
	private String toMobileNumber;
	
	@Column(name = "textMessage")
	private String textMessage;
	
	@Column(name = "priority")
	private String priority;

	public String getFromMobileNumber() {
		return fromMobileNumber;
	}

	public void setFromMobileNumber(String fromMobileNumber) {
		this.fromMobileNumber = fromMobileNumber;
	}

	public String getToMobileNumber() {
		return toMobileNumber;
	}

	public void setToMobileNumber(String toMobileNumber) {
		this.toMobileNumber = toMobileNumber;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	
}
