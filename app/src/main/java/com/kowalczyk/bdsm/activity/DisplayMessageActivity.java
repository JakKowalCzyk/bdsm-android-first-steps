package com.kowalczyk.bdsm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        changePassword();
    }

    private void displayMessage() {
        Button clickButton = (Button) findViewById(R.id.button3);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = ((EditText) findViewById(R.id.editText6)).getText().toString();
                Secret secret = getSecretDao().load(1L);
                if (BCrypt.checkpw(password, secret.getHashedPassword())) {
                    showMessageDialog(secret.getSecret());
                } else {
                    showMessageDialog("ERROR - Password doesn't match");
                }
            }
        });
    }

    private void changePassword() {
        Button clickButton = (Button) findViewById(R.id.button4);
        clickButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent i = new Intent(getApplicationContext(), ChangePasswordAcitvity.class);
                                               startActivity(i);
                                           }
                                       }

        );
    }

    private void showMessageDialog(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DisplayMessageActivity.this);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert112 = builder1.create();
        alert112.show();
    }

    private SecretDao getSecretDao() {
        return ((DatabaseInit) getApplication()).getDaoSession().getSecretDao();
    }
}