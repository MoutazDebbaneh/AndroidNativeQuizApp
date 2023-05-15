package com.example.quizapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizapp.models.Choice;
import com.example.quizapp.models.User;

import java.util.ArrayList;

public class ChoiceTable {

    // Table Name
    public static final String TABLE_NAME = "Choice";

    // Table Field Name
    public static final String C_ID = "_id";
    public static final String C_TEXT = "text";
    public static final String C_IS_CORRECT = "is_correct";
    public static final String C_QUESTION_ID = "question_id";

    // Table Create Query
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_TEXT + " text, "
            + C_IS_CORRECT + " INTEGER, "
            + C_QUESTION_ID + " INTEGER, "
            + "FOREIGN KEY (" + C_QUESTION_ID + ") REFERENCES "
            + QuestionTable.TABLE_NAME + "(" + QuestionTable.C_ID + "));";

    public static boolean insertChoice(Choice choice){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_TEXT, choice.getText());
        values.put(C_IS_CORRECT, choice.getIsCorrect());
        values.put(C_QUESTION_ID, choice.getQuestionId());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return (result != -1);
    }

    public static boolean updateChoice(Choice choice){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_TEXT, choice.getText());
        values.put(C_IS_CORRECT, choice.getIsCorrect());
        values.put(C_QUESTION_ID, choice.getQuestionId());
        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(choice.getId())});
        db.close();
        return (result == 1);
    }

    public static boolean deleteChoice(Choice choice){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(choice.getId())});
        db.close();
        return (result == 1);
    }

    public static Choice getChoice(int id){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount() == 0){
            cursor.close();
            db.close();
            return null;
        }
        Choice choice = new Choice();
        choice.setId(cursor.getInt(0));
        choice.setText(cursor.getString(1));
        choice.setIsCorrect(cursor.getInt(2) == 1);
        choice.setQuestionId(cursor.getInt(3));
        cursor.close();
        db.close();
        return choice;
    }

    public static ArrayList<Choice> getAll(){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<Choice> choices = new ArrayList<>();
        while(cursor.moveToNext()){
            Choice choice = new Choice();
            choice.setId(cursor.getInt(0));
            choice.setText(cursor.getString(1));
            choice.setIsCorrect(cursor.getInt(2) == 1);
            choice.setQuestionId(cursor.getInt(3));
            choices.add(choice);
        }
        cursor.close();
        db.close();
        return choices;
    }

}
