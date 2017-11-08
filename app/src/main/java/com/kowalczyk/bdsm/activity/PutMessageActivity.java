package com.kowalczyk.bdsm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kowalczyk.bdsm.DatabaseInit;
import com.kowalczyk.bdsm.R;
import com.kowalczyk.bdsm.model.Secret;
import com.kowalczyk.bdsm.model.SecretDao;

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

                                                      Secret secret = getSecretDao().load(1L);
                                                      secret.setSecret(redMessage);
                                                      getSecretDao().update(secret);

                                                      Intent i = new Intent(getApplicationContext(), DisplayMessageActivity.class);
                                                      startActivity(i);
                                                  }
                                              }
        );
    }

    private SecretDao getSecretDao() {
        return ((DatabaseInit) getApplication()).getDaoSession().getSecretDao();
    }

}
