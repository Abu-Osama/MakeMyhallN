package com.dcmindia.app.makemyhall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class Falsh_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falsh__screen);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(getApplication(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }

    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        // finish();
    }
}
