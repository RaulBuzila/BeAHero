package com.example.raulbuzila.beahero;

/**
 * Created by alin.ciuciu on 11/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class Tab3History extends Fragment{

    UserLocalStore localDB;
    Button submit ;
    RadioButton rJale, rHepatitus, rIcter, rSmok, rAlcohol;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabhistory, container, false);

        localDB = new UserLocalStore(this.getContext());

        rJale = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Inchisoare);
        rHepatitus = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Hepatita);
        rIcter = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Sex);
        rSmok = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Fumator);
        rAlcohol = (RadioButton)rootView.findViewById(R.id.radioButton_Da_Alcool);

        submit = (Button)rootView.findViewById(R.id.sumbit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ResultActivity.class));
            }
        });

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TakeData();
                return  true;
            }
        });


        return rootView;
    }

    public void TakeData() {
        if (rJale.isChecked()) {
            localDB.SetBool("rJale", true);
        } else {
            localDB.SetBool("rJale", false);
        }


        if (rHepatitus.isChecked()) {
            localDB.SetBool("rHepatitus", true);
        } else {
            localDB.SetBool("rHepatitus", false);
        }


        if (rIcter.isChecked()) {
            localDB.SetBool("rIcter", true);
        } else {
            localDB.SetBool("rIcter", false);
        }


        if (rSmok.isChecked()) {
            localDB.SetBool("rSmok", true);
        } else {
            localDB.SetBool("rSmok", false);
        }

        if (rAlcohol.isChecked()) {
            localDB.SetBool("rAlcohol", true);
        } else {
            localDB.SetBool("rAlcohol", false);
        }
    }
}
