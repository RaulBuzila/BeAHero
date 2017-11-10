package com.example.raulbuzila.beahero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {

    Button blogin;
    EditText etUsername, etPassword;
    UserLocalStore localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        etUsername =(EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        blogin = (Button) findViewById(R.id.bLogin);

        blogin.setOnClickListener(this);
        localDB = new UserLocalStore(this);
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(!localDB.Authenticate(username, password)) {
                    ((TextView)findViewById(R.id.loginErrorMessage))
                            .setText("Credentiale invalide!");
                }
                else{
                    startActivity(new Intent(this, HomeActivity.class));
                    localDB.SetUserLoggedIn(true);
                }

                break;
        }
    }
}
