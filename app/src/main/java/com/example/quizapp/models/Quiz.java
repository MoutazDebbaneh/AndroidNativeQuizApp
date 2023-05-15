package com.example.quizapp.models;

import java.util.List;

public class Quiz {
    private int id;
    private int mark;
    private int deservedMark;
    private boolean result;
    private String date;
    private List<Question> questionList;
    private int userId;

    public Quiz(){}

    public Quiz(int id, int mark, int deservedMark, boolean result, String date, List<Question> questionList, int userId) {
        this.id = id;
        this.mark = mark;
        this.deservedMark = deservedMark;
        this.result = result;
        this.date = date;
        this.questionList = questionList;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getDeservedMark() {
        return deservedMark;
    }

    public void setDeservedMark(int deservedMark) {
        this.deservedMark = deservedMark;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void addQuestion(Question question){
        this.questionList.add(question);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
