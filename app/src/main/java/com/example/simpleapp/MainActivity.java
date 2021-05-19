package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        Button b_login = (Button)findViewById(R.id.button);
        EditText ed_username = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText ed_password = (EditText)findViewById(R.id.editTextTextPassword);

//        //Log.d("PASS",pass);
//        SQLiteConnector db = new SQLiteConnector(MainActivity.this);
//        b_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pass = ed_password.getText().toString();
//                String pass_hash = Hash.Hashing(pass);
//                boolean isExist = db.checkUser(ed_username.getText().toString(),pass_hash);
//                Log.d("PASS", ed_password.getText().toString());
//                Log.d("Password", pass_hash);
//                if (isExist) {
//                Intent intent = new Intent(MainActivity.this,Welcome.class);
//                intent.putExtra("username",ed_username.getText().toString());
//                startActivity(intent);
//                } else {
//                    Toast.makeText(MainActivity.this,"Login Fail. Invalid Username or Password",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        b_login.setOnClickListener(new View.OnClickListener() {
            ConnectionHelper connhelp = new ConnectionHelper();
            @Override
            public void onClick(View view) {
                String pass_hash = Hash.Hashing(ed_password.getText().toString());
                boolean check = connhelp.checkUser(ed_username.getText().toString(), pass_hash);
                Log.v("Check",Boolean.toString(check));
                if (check){
                    Intent intent = new Intent(MainActivity.this,Welcome.class);
                    intent.putExtra("username",ed_username.getText().toString());
                    startActivity(intent);
                    ed_username.setText("");
                    ed_username.setHint("Username");
                    ed_password.setText("");
                    ed_password.setHint("Password");
                } else {
                    Toast.makeText(MainActivity.this,"Login Fail. Invalid Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button b_signup = (Button)findViewById(R.id.button3);
        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(MainActivity.this, Register.class);
                    startActivity(i);


            }
        });


    }
}