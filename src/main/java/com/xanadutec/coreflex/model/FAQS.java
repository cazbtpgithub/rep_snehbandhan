package com.xanadutec.coreflex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAQS")

public class FAQS {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "question")
	String question;
	
	@Column(name ="answer")
	 String answer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
