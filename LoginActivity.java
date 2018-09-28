package com.rff.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edAcct;
    private EditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {
        edAcct = findViewById(R.id.et_acct);
        edPass = findViewById(R.id.et_pass);
    }

    public void login(View view)
    {
        String acct = edAcct.getText().toString();
        String pass = edPass.getText().toString();

        if ("Robert".equals(acct) && "1234".equals(pass)) {
            setResult(RESULT_OK);
            finish();
        }
    }

    public void cancel(View view)
    {

    }
}
