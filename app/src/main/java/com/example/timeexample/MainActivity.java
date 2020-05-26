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

    //프로그래스bar를 초기화하는 함수
    public void initProg(){
        prog.setMax(6);//최대값 6 지정
        prog.setProgress(6); //현재값 6 지정
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
                        } else if (currprog == 0) {
                            currprog = 6;
                        }
                        prog.setProgress(currprog);
                    }
                }
        );
    }

}


