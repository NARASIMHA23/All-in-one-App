package com.subhanshu.loginform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by subhanshu singh on 29/07/2017.
 */

public class Splsh extends Activity {

    long Delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Timer RunSplash = new Timer();
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent in =new Intent(Splsh.this,MainActivity.class);
                startActivity(in);

            }
        };

        RunSplash.schedule(ShowSplash,Delay);
    }
}
