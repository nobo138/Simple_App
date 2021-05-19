package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Show welcome to username by getting username from MainActivity and Register
        TextView text = (TextView)findViewById(R.id.textView3);
        Bundle extras = getIntent().getExtras();
        String username = null;
        if (extras != null)
        {
            username = extras.getString("username");
            text.setText("Hello " + username); // welcome username
        }
        Button btn_logout = (Button)findViewById(R.id.button4);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Welcome.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}