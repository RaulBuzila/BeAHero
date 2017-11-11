package com.example.raulbuzila.beahero;


/**
 * Created by alin.ciuciu on 11/10/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;

public class Tab1PersonalData extends Fragment {

    EditText fullName, age, birthPlace;
    RadioButton masc, fem;

    UserLocalStore localDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabpersonaldata, container, false);

        fullName = (EditText)rootView.findViewById(R.id.textName);
        age = (EditText)rootView.findViewById(R.id.textVarsta);
        birthPlace = (EditText)rootView.findViewById(R.id.placeOfBirth);
        masc = (RadioButton)rootView.findViewById(R.id.radioButton_M);
        fem = (RadioButton)rootView.findViewById(R.id.radioButton_F);
        localDB = new UserLocalStore(this.getContext());

        if(localDB.GetUserLoggedIn()){
            User us = localDB.GetLoggedInUser();
            fullName.setText(us.name);
            age.setText(String.valueOf(us.age));
        }

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PutStuff();
                return  true;
            }
        });

        return rootView;
    }

    //@Override
    public void PutStuff(){
        //super.onDetach();

        localDB.SetString("birthPlace",birthPlace.getText().toString());
        if(!localDB.GetUserLoggedIn()) {
            localDB.SetString("name", fullName.getText().toString());
            localDB.SetInt("age", Integer.parseInt(age.getText().toString()));
        }

        localDB.SetString("birthPlace", birthPlace.getText().toString());

        if(masc.isChecked()) {
            localDB.SetBool("male", true);
        }else {
            localDB.SetBool("male", false);
        }
    }
}
