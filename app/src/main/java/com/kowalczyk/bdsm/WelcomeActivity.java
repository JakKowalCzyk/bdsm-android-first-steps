package com.kowalczyk.bdsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.mindrot.jbcrypt.BCrypt;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MessageStore.hashedPassword == null || MessageStore.hashedPassword.isEmpty()) {
            setContentView(R.layout.activity_welcome);
        } else {
            Intent i = new Intent(getApplicationContext(), DisplayMessageActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPassword();
                Intent i = new Intent(WelcomeActivity.this, PutMessageActivity.class);
                startActivity(i);
            }
        });
    }

    private void readPassword() {
        String password = ((EditText) findViewById(R.id.editText)).getText().toString();
        MessageStore.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
    }


}
