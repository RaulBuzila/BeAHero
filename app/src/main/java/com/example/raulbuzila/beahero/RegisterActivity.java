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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etAge, etUsername, etPassword;
    TextView etErrorMesage;
    UserLocalStore localDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etErrorMesage = (TextView) findViewById(R.id.errorMessage);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);
        localDb = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bRegister:
                String name  = etName.getText().toString();
                String password = etPassword.getText().toString();

                if(etAge.getText().toString().length() == 0)
                {
                    etErrorMesage.setText("Utilizator invalid!");
                    return;
                }

                int age;
                try {
                  age = Integer.parseInt(etAge.getText().toString());
                }
                catch (Exception ex)
                {
                    etErrorMesage.setText("Introduceti numar va rog!");
                    return;
                }

                String username = etUsername.getText().toString();
                User user = new User(name, username, password, age);

                if(name.length() == 0 || username.length() == 0 || age < 15 || password.length() == 0) {
                    etErrorMesage.setText("Utilizator invalid!");
                    return;
                }

                localDb.StoreUser(user);
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
