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
import android.widget.RadioButton;

public class Tab2Health extends Fragment{


    RadioButton rHealth, rMedicine, rSexContact, rDrugs, rBlood;

    UserLocalStore localDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabhealth, container, false);

        localDB = new UserLocalStore(this.getContext());

        rHealth = (RadioButton)rootView.findViewById(R.id.radioButton_Yes_GoodHealth);
        rMedicine = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Tratament);
        rSexContact = (RadioButton)rootView.findViewById(R.id.radioButton_Da_ContactSexual);
        rDrugs = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Droguri);
        rBlood = (RadioButton)rootView.findViewById(R.id.radioButton_Da_UltimaDonatie);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PutStuff();
                return  true;
            }
        });

        return rootView;
    }

    public void PutStuff(){
        if(rHealth.isChecked()) {
            localDB.SetBool("rHealth", true);
        }else {
            localDB.SetBool("rHealth", false);
        }


        if(rMedicine.isChecked()) {
            localDB.SetBool("rMedicine", true);
        }else {
            localDB.SetBool("rMedicine", false);
        }


        if(rSexContact.isChecked()) {
            localDB.SetBool("rSexContact", true);
        }else {
            localDB.SetBool("rSexContact", false);
        }


        if(rDrugs.isChecked()) {
            localDB.SetBool("rDrugs", true);
        }else {
            localDB.SetBool("rDrugs", false);
        }


        if(rBlood.isChecked()) {
            localDB.SetBool("rBlood", true);
        }else {
            localDB.SetBool("rBlood", false);
        }

    }
}
