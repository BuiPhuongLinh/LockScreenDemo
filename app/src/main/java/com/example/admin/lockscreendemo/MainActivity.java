package com.example.admin.lockscreendemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.example.admin.lockscreendemo.object.LookScreenSevice;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView tvDate;
    TextView tvTime;
    Button btUnlock;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.e("ERR","getLayoutParams");
        getLayoutParams();
        startService(new Intent(MainActivity.this, LookScreenSevice.class));


        setContentView(R.layout.activity_main);






    }

    public void getLayoutParams() {
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_lockscreen, null);

        tvTime= (TextView) view.findViewById(R.id.tvTime);
        tvDate= (TextView) view.findViewById(R.id.tvDate);
        btUnlock= (Button) view.findViewById(R.id.btUnlock);
        btUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("ERR","unlock");
                android.os.Process.killProcess(android.os.Process.myPid());
                //finish();

            }
        });

        SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = day.format(new Date());
        tvDate.setText(currentDate);
        System.out.print(tvDate);

        String strDateFormat12 = "hh:mm a";
        SimpleDateFormat time = new SimpleDateFormat(strDateFormat12);
        String currentTime = time.format(new Date());
        tvTime.setText(currentTime);



        WindowManager.LayoutParams params=new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                PixelFormat.TRANSPARENT);

        windowManager.addView(view,params);

    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }


}
