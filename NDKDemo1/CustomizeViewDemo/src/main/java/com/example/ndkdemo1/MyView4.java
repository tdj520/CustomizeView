package com.example.ndkdemo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView4 extends View {
    private Paint mPaint;
    private Paint mPaint1;
    private int color = Color.RED;
    private Point mPoint;
    private float mCircleAlpha1 = 0;

    public MyView4(Context context) {
        super(context);
    }
    private float mScale = 1.0f;
    private float mRadius;
    private float mCircleAlpha = 1;
    private Paint textPaint;
    private Paint textPaint1;
    private int mTextDis;
    public MyView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint1 = new Paint();
        textPaint = new Paint();
        textPaint1 = new Paint();
        mPaint.setColor(color);
        mPoint = new Point(100,0);
        mPaint1.setAntiAlias(true);

    }
    public void setColor(int color){
        this.color = color;
        mPaint.setColor(color);

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
        canvas.scale(mScale,mScale);
        canvas.drawCircle(getWidth()/2,getHeight()/2,50,mPaint);
        mPaint.setStrokeWidth(50);
        canvas.drawPoint(mPoint.x,mPoint.y,mPaint);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(3);

        mPaint1.setAlpha((int) (255 * mCircleAlpha));
        canvas.drawCircle(getWidth()/2,getHeight()/2,mRadius,mPaint1);

        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(20);
        textPaint.setAlpha((int) (255 * mCircleAlpha));
        canvas.drawText("1999",getWidth()/2,getHeight()/2-mTextDis,textPaint);

        textPaint1.setColor(Color.GREEN);
        textPaint1.setTextSize(20);
        textPaint1.setAlpha((int) (255 * mCircleAlpha1));
        Rect rect = new Rect();
        textPaint1.getTextBounds("点赞---1", 0, "点赞---1".length(), rect);
        int h = rect.height();
        canvas.drawText("2000",getWidth()/2,h+getHeight()/2-mTextDis,textPaint1);
    }
    public void setTestScale(float scale){
        this.mScale = scale;
        invalidate();
    }
    public void setRadius(float radius){
        this.mRadius = radius;
        invalidate();
    }
    public void setCircleAlpha(float circleAlpha){
        this.mCircleAlpha = circleAlpha;
        invalidate();
    }
    public void setCircleAlpha1(float circleAlpha){
        this.mCircleAlpha1 = circleAlpha;
        invalidate();
    }
    public void setTextDis(int textDis){
        this.mTextDis = textDis;
        Log.d("zpp","textDis..."+textDis);
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

          setMeasuredDimension(200,200);
    }
}
