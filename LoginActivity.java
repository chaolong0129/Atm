package com.rff.atm;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edAcct;
    private EditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // File Write to Android
        /*getSharedPreferences("atm", MODE_PRIVATE)
                .edit()
                .putInt("Level", 3)
                .putString("NAME", "Robert")
                .commit();*/
        // File Read from Android
        /*int level = getSharedPreferences("atm", MODE_PRIVATE)
                .getInt("Level", 0);*/
        /*Log.d(TAG, "onCreate: " + level);*/
        findViews();
        String acct = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("USERID", "");
        if (!acct.isEmpty())
            edAcct.setText(acct);
    }

    private void findViews() {
        edAcct = findViewById(R.id.et_acct);
        edPass = findViewById(R.id.et_pass);
    }

    public void login(View view)
    {
        final String acct = edAcct.getText().toString();
        final String pass = edPass.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(acct).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String)dataSnapshot.getValue();
                        if (pw.equals(pass)){
                            getSharedPreferences("atm", MODE_PRIVATE)
                                    .edit()
                                    .putString("USERID", acct)
                                    .apply();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Error")
                                    .setMessage("Login Fail")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void cancel(View view)
    {

    }
}
