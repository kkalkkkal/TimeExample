package com.example.timeexample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    TextView textView; // 숫자가 나오는 텍스트뷰
    MyTimer myTimer; // 타이머 객체 생성
    ProgressBar prog = null;
    int MAX_Timer = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prog= (ProgressBar)findViewById(R.id.TimerProgressBar);
        initProg();


        textView = findViewById(R.id.textView2);
        myTimer = new MyTimer(60000, 1000);
    }

    public void clickHandler(View view)
    {
        switch(view.getId())
        {
            case R.id.btnStart:
                myTimer.start();//
                //decreaseBar();
                break;
            case R.id.btnReset :
                myTimer.cancel();
                textView.setText("60 초");
                initProg();
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
            decreaseBar();
        }

        @Override
        public void onFinish() {
            textView.setText("0 초");
        }
    }

    //프로그래스bar를 초기화하는 함수
    public void initProg(){
        prog.setMax(MAX_Timer);//최대값 10 지정
        prog.setProgress(MAX_Timer); //현재값 10 지정
    }


    public void decreaseBar() { // 타이머 바 줄어드는 함수
        runOnUiThread( //progressBar는 ui에 해당하므로 runOnUiThread로 컨트롤해야한다
                new Runnable() { //thread구동과 마찬가지로 Runnable을 써주고

                    @Override
                    public void run() { //run을 해준다. 그러나 일반 thread처럼 .start()를 해줄 필요는 없다
                        // TODO Auto-generated method stub
                        int currprog = prog.getProgress();

                        if (currprog > 0) {
                            currprog = currprog - 1;
                        } else if (currprog == 0) { // 타이머가 끝났을 때
                            currprog = MAX_Timer;
                        }
                        prog.setProgress(currprog);
                    }
                }
        );
    }

}


