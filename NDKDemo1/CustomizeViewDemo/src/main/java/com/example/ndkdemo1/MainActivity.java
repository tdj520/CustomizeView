package com.example.ndkdemo1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    private Button btn;
    private Button btn1;
    private int mCount;
    private MyView1 myView1;
    private int dis;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv.setText(stringFromJNI());
        myView = findViewById(R.id.myView);
        myView1 = findViewById(R.id.myView1);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                mCount++;
                dis+=10;
                myView1.setDis(dis);
                if(mCount%3 == 0){
                    myView.setColorFilter(0x00ffff);
                }else if(mCount%3 == 1){
                    myView.setColorFilter(0xff00ff);
                }else if(mCount%3 == 2){
                    myView.setColorFilter(0xffff00);
                }

            }
        });
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
