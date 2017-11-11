package com.example.raulbuzila.beahero;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

public class HistoryActivity extends AppCompatActivity {

    Chronometer countDownTimer;
    CountDownTimer timer;

    UserLocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoy);

        final TextView countdown = (TextView)findViewById(R.id.counter);

        long miliseconds;

        localStore = new UserLocalStore(this);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format1.parse(localStore.GetString("formatedData"));
            miliseconds =  date1.getTime() + 70*24*60*1000 - new Date().getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            miliseconds = 70*24*60*1000;
        }
        timer = new CountDownTimer(miliseconds, 1000) {
            @Override
            public void onTick(long millis) {
                //countdown.setText(millisUntilFinished / 1000+ " secunde ramase.");
                countdown.setText(String.format("%d zile %d ore %d min %d sec",
                        TimeUnit.MILLISECONDS.toDays(millis),
                        TimeUnit.MILLISECONDS.toHours(millis) % 24,
                        TimeUnit.MILLISECONDS.toMinutes(millis)%60,
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                ));
                //String.valueOf((int) (millisUntilFinished / (1000*60*60*24)))
            }

            @Override
            public void onFinish() {
                countdown.setText("done!");
            }
        }.start();

    }
}
