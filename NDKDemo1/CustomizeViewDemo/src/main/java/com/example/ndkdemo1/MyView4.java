package com.example.ndkdemo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView4 extends View {
    private Paint mPaint;
    private int color = Color.RED;
    private Point mPoint;
    public MyView4(Context context) {
        super(context);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(color);
        mPoint = new Point(100,0);
    }
    public void setColor(int color){
        this.color = color;
        mPaint.setColor(color);
        Log.d("zpp","setColor...",new Exception());
        invalidate();
    }
    public int getColor(int color){
        return this.color;
    }
    public void setPosition(Point p){
        Log.d("dongjiao","PointEvaluator...p.x = :"+p.x+",p.y = :"+p.y);

        mPoint.set(p.x,p.y);
        invalidate();
    }
    public Point getPosition(){
        return mPoint;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(50,50,50,mPaint);
        mPaint.setStrokeWidth(50);
        canvas.drawPoint(mPoint.x,mPoint.y,mPaint);
    }
}
