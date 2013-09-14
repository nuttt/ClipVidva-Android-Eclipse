package com.example.clipvidva.quizzes;

/**
 * Created by nuttt on 12/9/13.
 */
public class Quiz {
    private int id;
    private int subject_id;
    private String question;
    private String type;
    private String choices;
    private String answer;
    private String hint;
    private String description;


    public Quiz() {
    }

    public Quiz(int id, int subject_id, String question, String type, String choices, String answer, String hint, String description) {
        this.id = id;
        this.subject_id = subject_id;
        this.question = question;
        this.type = type;
        this.choices = choices;
        this.answer = answer;
        this.hint = hint;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
