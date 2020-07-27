package com.example.ndkdemo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class MyView6 extends EditText {
    public MyView6(Context context) {
        super(context);
    }

    public MyView6(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);

    }
}
