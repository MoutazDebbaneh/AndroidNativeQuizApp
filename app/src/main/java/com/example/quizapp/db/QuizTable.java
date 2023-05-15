package com.example.quizapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.models.User;

import java.util.ArrayList;

public class QuizTable {

    // Table Name
    public static final String TABLE_NAME = "Quiz";

    // Table Field Name
    public static final String C_ID = "_id";
    public static final String C_MARK = "mark";
    public static final String C_DESERVED_MARK = "deserved_mark";
    public static final String C_RESULT = "result";
    public static final String C_DATE = "date";
    public static final String C_USER_ID = "user_id";

    // Table Create Query
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_MARK + " INTEGER, "
            + C_DESERVED_MARK + " INTEGER, "
            + C_RESULT + " INTEGER, "
            + C_DATE + " text, "
            + C_USER_ID + " INTEGER, "
            + "FOREIGN KEY (" + C_USER_ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable.C_ID + "));";

    public static boolean insertQuiz(Quiz quiz){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_MARK, quiz.getMark());
        values.put(C_DESERVED_MARK, quiz.getDeservedMark());
        values.put(C_RESULT, quiz.getResult());
        values.put(C_DATE, quiz.getDate());
        values.put(C_USER_ID, quiz.getUserId());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        if(result == -1) return false;
        boolean is_successful = true;
        for(Question question: quiz.getQuestionList()) {
            if(!QuestionTable.insertQuestion(question)) is_successful = false;
        }
        return is_successful;
    }

    public static boolean updateQuiz(Quiz quiz){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_MARK, quiz.getMark());
        values.put(C_DESERVED_MARK, quiz.getDeservedMark());
        values.put(C_RESULT, quiz.getResult());
        values.put(C_DATE, quiz.getDate());
        values.put(C_USER_ID, quiz.getUserId());
        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(quiz.getId())});
        db.close();
        return (result == 1);
    }

    public static boolean deleteQuiz(Quiz quiz){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(quiz.getId())});
        db.close();
        return (result == 1);
    }

    public static Quiz getQuiz(int id){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount() == 0){
            cursor.close();
            db.close();
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setId(cursor.getInt(0));
        quiz.setMark(cursor.getInt(1));
        quiz.setDeservedMark(cursor.getInt(2));
        quiz.setResult(cursor.getInt(3) == 1);
        quiz.setDate(cursor.getString(4));
        quiz.setUserId(cursor.getInt(5));
        cursor.close();
        db.close();
        return quiz;
    }

    public static ArrayList<Quiz> getAll(){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        while(cursor.moveToNext()){
            Quiz quiz = new Quiz();
            quiz.setId(cursor.getInt(0));
            quiz.setMark(cursor.getInt(1));
            quiz.setDeservedMark(cursor.getInt(2));
            quiz.setResult(cursor.getInt(3) == 1);
            quiz.setDate(cursor.getString(4));
            quiz.setUserId(cursor.getInt(5));
            quizzes.add(quiz);
        }
        cursor.close();
        db.close();
        return quizzes;
    }

}
