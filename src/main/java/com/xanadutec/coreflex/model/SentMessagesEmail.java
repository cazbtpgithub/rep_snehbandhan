package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "SentMessagesEmail")
public class SentMessagesEmail {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "msg_email_Id", nullable = false)
	private long msg_email_Id;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "sent_msg_email_userModel_id")
	private UserModel sent_msg_email_userModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "received_msg_email_UserModel_id")
	private UserModel received_msg_email_UserModel;

	@Column(name = "communication_Date")
	private Date msgDate;
	
	@Column(name = "message",length=2000)
	private String message;

	@Column(name = "communication_Type")
	private String type;

	public long getMsg_email_Id() {
		return msg_email_Id;
	}

	public void setMsg_email_Id(long msg_email_Id) {
		this.msg_email_Id = msg_email_Id;
	}

	public UserModel getSent_msg_email_userModel() {
		return sent_msg_email_userModel;
	}

	public void setSent_msg_email_userModel(UserModel sent_msg_email_userModel) {
		this.sent_msg_email_userModel = sent_msg_email_userModel;
	}

	public UserModel getReceived_msg_email_UserModel() {
		return received_msg_email_UserModel;
	}

	public void setReceived_msg_email_UserModel(UserModel received_msg_email_UserModel) {
		this.received_msg_email_UserModel = received_msg_email_UserModel;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
