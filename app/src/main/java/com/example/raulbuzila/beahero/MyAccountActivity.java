package com.example.raulbuzila.beahero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyAccountActivity extends AppCompatActivity {

    UserLocalStore localDB;
    TextView accName, accUsername, accAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        localDB = new UserLocalStore(this);

        if(localDB.GetUserLoggedIn()) {
            accName = (TextView)findViewById(R.id.acc_name_id);
            accUsername = (TextView)findViewById(R.id.acc_username_id);
            accAge = (TextView)findViewById(R.id.acc_age_id);

            User us = localDB.GetLoggedInUser();
            accName.setText(us.name);
            accUsername.setText(us.username);
            accAge.setText(String.valueOf(us.age));
        }
    }
}
