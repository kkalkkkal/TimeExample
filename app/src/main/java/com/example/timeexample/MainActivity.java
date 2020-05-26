package com.example.timeexample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    MyTimer myTimer;//ghjdfg

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        myTimer = new MyTimer(60000, 1000);
    }

    public void clickHandler(View view)
    {
        switch(view.getId())
        {
            case R.id.btnStart:
                myTimer.start();//
                break;
            case R.id.btnReset :
                myTimer.cancel();
                textView.setText("60 초");
                break;

        }

    }

    class MyTimer extends CountDownTimer
    {
        public MyTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText(millisUntilFinished/1000 + " 초");
        }

        @Override
        public void onFinish() {
            textView.setText("0 초");
        }
    }
}


