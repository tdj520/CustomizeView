package com.example.ndkdemo1;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

public class SecondActivity extends Activity {
    private Button btn;
    private ImageView imageView;
    private Button btn1;
    private int transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        btn = findViewById(R.id.btn);
        imageView = findViewById(R.id.image);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                /*Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                            imageView.setTranslationX(transaction);
                            transaction+=10;
                    }
                };
                for (int i = 0;i <= 30;i++){
                    imageView.postDelayed(runnable,i * 100);
                }*/
                //局限性:仅仅只能操作View给你的几个属性,无法操作自定义属性
                ViewPropertyAnimator viewPropertyAnimator =  imageView.animate().setDuration(1000).translationX(300).alpha(0.5f);
                //设置插值器，插值器在动画中意思是：根据动画时间完成度计算动画完成度
                //viewPropertyAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                viewPropertyAnimator.setInterpolator(new LinearInterpolator());
                viewPropertyAnimator.start();
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("ObjectAnimatorBinding") ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"disX",30);
                objectAnimator.setDuration(5000 ).start();
            }
        });
    }

}
