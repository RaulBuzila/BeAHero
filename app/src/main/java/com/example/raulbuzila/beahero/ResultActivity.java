package com.example.raulbuzila.beahero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    UserLocalStore localDB;
    TextView tName, tErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        localDB = new UserLocalStore(this);

        String name = localDB.GetString("name");
        tName  = (TextView)findViewById(R.id.result_username);
        tErrors = (TextView)findViewById(R.id.result_errors);

        tName.setText(name);

        ValidateUser();
    }

    private  void ValidateUser(){
        boolean isMale = localDB.GetBool("male");
        int age = localDB.GetInt("age");

        if(!ValidateHealth(age))
            return;

       if(!ValidateHistory()){
           return;
       }

        tErrors.setText("Puteti dona" + (new Date()).toString());
    }

    private boolean ValidateHistory(){
        boolean rJale = localDB.GetBool("rJale");
        if(rJale)
        {
            tErrors.setText("Nu puteti dona datorita incarcerarii.");
            return  false;
        }

        boolean rHepatitus = localDB.GetBool("rHepatitus");
        if(rHepatitus)
        {
            tErrors.setText("Nu puteti dona datorita hepatitei");
            return  false;
        }

        boolean rIcter = localDB.GetBool("rIcter");
        if(rIcter)
        {
            tErrors.setText("Nu puteti dona datorita din cauza bolilor");
            return  false;
        }


        boolean rSmok = localDB.GetBool("rSmok");
        if(rSmok)
        {
            tErrors.setText("Nu puteti dona datorita fumatului");
            return  false;
        }

        boolean rAlcohol = localDB.GetBool("rAlcohol");
        if(rAlcohol)
        {
            tErrors.setText("Nu puteti dona datorita  alcoolului");
            return  false;
        }

        return  true;
    }

    private boolean ValidateHealth(int age) {
        if(age<14 || age > 60)
        {
            tErrors.setText("Varsta necompatibila.");
            return  false;
        }

        boolean rHealth = localDB.GetBool("rHealth");
        if(!rHealth)
        {
            tErrors.setText("Nesanatos");
            return  false;
        }


        boolean rMedicine = localDB.GetBool("rMedicine");
        if(rMedicine)
        {
            tErrors.setText("Nu puteti dona in timpul unui tratament.");
            return  false;
        }

        boolean rSexContact = localDB.GetBool("rSexContact");
        if(rSexContact)
        {
            tErrors.setText("Nu puteti dona.");
            return  false;
        }

        boolean rDrugs = localDB.GetBool("rDrugs");
        if(rDrugs)
        {
            tErrors.setText("Nu puteti dona datorita consumului de droguri.");
            return  false;
        }

        boolean rBlood = localDB.GetBool("rBlood");
        if(rBlood)
        {
            tErrors.setText("Nu puteti dona datorita pentru a ati pierdut mult sange.");
            return  false;
        }


        return  true;
    }
}

