package com.kowalczyk.bdsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class PutMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        readMessage();
    }

    private void readMessage() {
        Button clickButtonMessage = (Button) findViewById(R.id.button2);
        clickButtonMessage.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      String redMessage = ((EditText) findViewById(R.id.editText5)).getText().toString();
                                                      MessageStore.message = new String(Hex.encodeHex(DigestUtils.md5(redMessage)));
                                                      Intent i = new Intent(getApplicationContext(), DisplayMessageActivity.class);
                                                      startActivity(i);
                                                  }
                                              }
        );
    }
}
