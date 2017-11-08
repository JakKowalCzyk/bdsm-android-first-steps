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

public class ChangePasswordAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_acitvity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button clickButton = (Button) findViewById(R.id.button5);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassValid()) {
                    saveNewPassword();
                    showPasswordDialog("Password has been changed");
                } else {
                    showPasswordDialog("Wrong password, cannot provide new one");
                }
            }
        });
    }

    private void showPasswordDialog(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ChangePasswordAcitvity.this);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        goToDisplayMsgActivity();
                    }
                });
        AlertDialog alert112 = builder1.create();
        alert112.show();
    }

    private boolean oldPassValid() {
        String password = ((EditText) findViewById(R.id.editText2)).getText().toString();
        return (BCrypt.checkpw(password, getSecretDao().load(1L).getHashedPassword()));
    }

    private void saveNewPassword() {
        String password = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        Secret secret = getSecretDao().load(1L);
        secret.setHashedPassword(hashedPassword);
        getSecretDao().update(secret);
    }

    private void goToDisplayMsgActivity() {
        Intent i = new Intent(getApplicationContext(), DisplayMessageActivity.class);
        startActivity(i);
    }

    private SecretDao getSecretDao() {
        return ((DatabaseInit) getApplication()).getDaoSession().getSecretDao();
    }
}
