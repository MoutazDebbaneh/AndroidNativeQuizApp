package com.example.quizapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelperInstance = null;
    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public static DBHelper getInstance(Context ctx) {
        if (dbHelperInstance == null) {
            dbHelperInstance = new DBHelper(ctx.getApplicationContext());
        }
        return dbHelperInstance;
    }

    private DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_QUERY);
        db.execSQL(QuizTable.CREATE_QUERY);
        db.execSQL(QuestionTable.CREATE_QUERY);
        db.execSQL(ChoiceTable.CREATE_QUERY);

        System.out.println("\n\n\n\nCREATED DB\n\n\n\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
