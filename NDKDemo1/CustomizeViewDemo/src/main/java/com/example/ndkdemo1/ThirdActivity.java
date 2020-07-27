package com.example.ndkdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zpp","ThirdActivity...onTouchEvent...event = :"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("zpp","ThirdActivity...dispatchTouchEvent...event = :"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
