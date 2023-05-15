package com.example.quizapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizapp.models.User;

import java.util.ArrayList;

public class UserTable {

    // Table Name
    public static final String TABLE_NAME = "User";

    // Table Field Name
    public static final String C_ID = "_id";
    public static final String C_USERNAME = "username";

    // Table Create Query
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_USERNAME + " text )";

    public static boolean insertUser(User user){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_USERNAME, user.getUsername());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return (result != -1);
    }

    public static boolean updateUser(User user){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_USERNAME, user.getUsername());
        long result = db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return (result == 1);
    }

    public static boolean deleteUser(User user){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return (result == 1);
    }

    public static User getUser(int id){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.getCount() == 0){
            cursor.close();
            db.close();
            return null;
        }
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setUsername(cursor.getString(1));
        cursor.close();
        db.close();
        return user;
    }

    public static ArrayList<User> getAll(){
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<User> users = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            users.add(user);
        }
        cursor.close();
        db.close();
        return users;
    }
}
