package com.kowalczyk.bdsm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.mindrot.jbcrypt.BCrypt;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayMessage();
    }

    private void displayMessage() {
        Button clickButton = (Button) findViewById(R.id.button3);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = ((EditText) findViewById(R.id.editText6)).getText().toString();
                TextView tv = (TextView) findViewById(R.id.textView5);
                if (BCrypt.checkpw(password, MessageStore.hashedPassword)) {
                    try {
                        tv.setText(new String(Hex.decodeHex(MessageStore.message.toCharArray())));
                    } catch (DecoderException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("ERROR - Password doesn't match");
                }
            }
        });
    }
}
