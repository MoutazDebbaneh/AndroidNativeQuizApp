package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizapp.db.DBHelper;
import com.example.quizapp.db.UserTable;
import com.example.quizapp.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBHelper dbHelper = DBHelper.getInstance(this);
        UserTable.insertUser(new User(0, "New User"));
        System.out.println("\n\n\n\n\n\n");
        ArrayList<User> u = UserTable.getAll();
        String output = u.get(u.size() - 1).getUsername();
        System.out.println("\n\n\n\n\n\n");
        System.out.println(output);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}