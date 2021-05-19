package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button b_signup = (Button)findViewById(R.id.button2);
        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed_username = (EditText)findViewById(R.id.editTextTextPersonName2);
                EditText ed_password = (EditText)findViewById(R.id.editTextTextPassword2);
                EditText ed_email = (EditText)findViewById(R.id.editTextTextEmailAddress);

                String s_username= ed_username.getText().toString();
                String s_password = ed_password.getText().toString();
                String s_email = ed_email.getText().toString();
                String result;
                if (s_username.isEmpty() || s_email.isEmpty() || s_password.isEmpty()) {
                    Toast.makeText(Register.this,"Register Fail. Please enter full!! ",Toast.LENGTH_SHORT).show();
                } else
                {
                   // Hash password
                    String password_hash = Hash.Hashing(s_password);
                    Log.d("Password hashed",password_hash);

                    ConnectionHelper conn_add = new ConnectionHelper();
                    if (conn_add.checkUser(s_username,password_hash)){
                        Log.d("check", s_username);
                        Log.d("check", password_hash);
                        result = "User Exists. Try again.";
                    } else
                    {
                        conn_add.addUser("1",s_username,password_hash,s_email);
                        Log.d("ADD USER", "Success");
                        if (conn_add.checkUser(s_username,password_hash))
                        {
                            result = "Create User Successfully.";
                            Intent intent = new Intent(Register.this, Welcome.class);
                            intent.putExtra("username", s_username);
                            startActivity(intent);
                            ed_username.setText("");
                            ed_username.setHint("Username");
                            ed_password.setText("");
                            ed_password.setHint("Password");
                            ed_email.setText("");
                            ed_email.setHint("Email");
                        }
                        else
                        {
                            Log.d("ADD USER", password_hash);
                            result = "Something is wrong. Try again.";
                        }
                    }
                    Toast.makeText(Register.this, result,Toast.LENGTH_SHORT).show();


//                    //Save data into database
//                    SQLiteConnector db = new SQLiteConnector(Register.this);
//                    if(db.checkUser(s_username,password_hash)) {
//                        result = "User exits!";
//                    } else {
//                        db.addUser(new User(1, s_username, s_email, password_hash));//insert into database
//
//                        if (db.checkUser(s_username, password_hash)) { //check add successfully ??
//                            result = "Create account succesfully!!";
//
//                            //Transfer username to Welcome
//                            Intent intent = new Intent(Register.this, Welcome.class);
//                            intent.putExtra("username", s_username);
//                            startActivity(intent);
//                        } else {
//                            result = "Something is wrong. Try again!!";
//                        }
//                    }
//                    Toast.makeText(Register.this,result,Toast.LENGTH_SHORT).show();
                   }
            }
        });
    }

}

