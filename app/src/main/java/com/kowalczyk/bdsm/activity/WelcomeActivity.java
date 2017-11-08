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
import org.mindrot.jbcrypt.BCrypt;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private SecretDao getSecretDao() {
        return ((DatabaseInit) getApplication()).getDaoSession().getSecretDao();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSecretDao().loadAll().isEmpty()) {
            setContentView(R.layout.activity_welcome);
            Button clickButton = (Button) findViewById(R.id.button);
            clickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readPassword();
                    Intent i = new Intent(WelcomeActivity.this, PutMessageActivity.class);
                    startActivity(i);
                }
            });
        } else {
            Intent i = new Intent(getApplicationContext(), DisplayMessageActivity.class);
            startActivity(i);
        }
    }

    private void readPassword() {
        String password = ((EditText) findViewById(R.id.editText)).getText().toString();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        Secret secret = new Secret();
        secret.setHashedPassword(hashedPassword);

        getSecretDao().save(secret);
    }


}
