package com.example.quizapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizapp.models.Choice;
import com.example.quizapp.models.Question;
import com.example.quizapp.db.QuizTable;

import java.util.ArrayList;

public class QuestionTable {

    // Table Name
    public static final String TABLE_NAME = "Question";

    // Table Field Name
    public static final String C_ID = "_id";
    public static final String C_TEXT = "text";
    public static final String C_CATEGORY = "category";
    public static final String C_QUIZ_ID = "quiz_id";

    // Table Create Query
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_TEXT + " text, "
            + C_CATEGORY + " text, "
            + C_QUIZ_ID + " INTEGER, "
            + "FOREIGN KEY (" + C_QUIZ_ID + ") REFERENCES "
            + QuizTable.TABLE_NAME + "(" + QuizTable.C_ID + "));";

    public static boolean insertQuestion(Question question) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_TEXT, question.getText());
        values.put(C_CATEGORY, question.getCategory());
        values.put(C_QUIZ_ID, question.getQuizId());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        if(result == -1) return false;
        boolean is_successful = true;
        for (Choice choice : question.getChoices()) {
            if(!ChoiceTable.insertChoice(choice)) is_successful = false;
        }
        return is_successful;
    }

    public static boolean updateQuestion(Question question){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_TEXT, question.getText());
        values.put(C_CATEGORY, question.getCategory());
        values.put(C_QUIZ_ID, question.getQuizId());
        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(question.getId())});
        db.close();
        return (result == 1);
    }

    public static boolean deleteQuestion(Question question){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(question.getId())});
        db.close();
        return (result == 1);
    }

    public static Question getQuestion(int id){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount() == 0){
            cursor.close();
            db.close();
            return null;
        }
        Question question = new Question();
        question.setId(cursor.getInt(0));
        question.setText(cursor.getString(1));
        question.setCategory(cursor.getString(2));
        question.setQuizId(cursor.getInt(3));
        cursor.close();
        db.close();
        return question;
    }

    public static ArrayList<Question> getAll(){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<Question> questions = new ArrayList<>();
        while(cursor.moveToNext()){
            Question question = new Question();
            question.setId(cursor.getInt(0));
            question.setText(cursor.getString(1));
            question.setCategory(cursor.getString(2));
            question.setQuizId(cursor.getInt(3));
            questions.add(question);
        }
        cursor.close();
        db.close();
        return questions;
    }

}
