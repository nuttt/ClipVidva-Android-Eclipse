package com.example.clipvidva.quizzes;

public class UserAnswer {
	private int subject_id;
	private int question_id;
	private String answer;
	private String result;
	
	
	public UserAnswer() {
		super();
	}

	public UserAnswer(int subject_id, int question_id, String answer,
			String result) {
		super();
		this.subject_id = subject_id;
		this.question_id = question_id;
		this.answer = answer;
		this.result = result;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}	
	
}
