package com.example.quizapp.models;

import android.util.Pair;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<Choice> choices;
    private String category;
    private int quizId;

    public Question() {
    }

    public Question(int id, String text, List<Choice> choices, String category, int quizId) {
        this.id = id;
        this.text = text;
        this.choices = choices;
        this.category = category;
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
