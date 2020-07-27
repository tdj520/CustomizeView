package com.example.ndkdemo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

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
        mPoint = new Point(SystemUtil.dp2px(getContext(),100),0);
        mPaint1.setAntiAlias(true);
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView4);
        if(typedArray!=null){
            String str = typedArray.getString(R.styleable.MyView4_customAttrText);
            Log.d("dongjiao","MyView4_customAttrText = ："+str);
        }

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
        canvas.drawCircle(getWidth()/2,getHeight()/2,SystemUtil.dp2px(getContext(),40),mPaint);
        mPaint.setStrokeWidth(SystemUtil.dp2px(getContext(),50));
        canvas.drawPoint(mPoint.x,mPoint.y,mPaint);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(SystemUtil.dp2px(getContext(),3));

        mPaint1.setAlpha((int) (255 * mCircleAlpha));
        canvas.drawCircle(getWidth()/2,getHeight()/2,mRadius,mPaint1);

        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(SystemUtil.dp2px(getContext(),20));
        textPaint.setAlpha((int) (255 * mCircleAlpha));
        canvas.drawText("1999",getWidth()/2,getHeight()/2-mTextDis,textPaint);

        //textPaint1.setColor(Color.GREEN);
        //textPaint1.setTextSize(20);
        textPaint.setAlpha((int) (255 * mCircleAlpha1));
        float textH = textPaint.getFontSpacing();  //获取绘制text高度
        canvas.drawText("2000",getWidth()/2,textH+getHeight()/2-mTextDis,textPaint);
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

        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(SystemUtil.dp2px(getContext(),190),SystemUtil.dp2px(getContext(),90));
        Log.d("zpp","getMeasuredWidth = :"+getMeasuredWidth()+",getMeasuredHeight = :"+getMeasuredHeight());
        int width = resolveSize(SystemUtil.dp2px(getContext(),190),widthMeasureSpec);
        int height = resolveSize(SystemUtil.dp2px(getContext(),90),heightMeasureSpec);
        Log.d("zpp","width = :"+width+",height = :"+height+","+MeasureSpec.getSize(widthMeasureSpec)+","+MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
       // LinearLayout.LayoutParams layoutParams =  (LinearLayout.LayoutParams) getLayoutParams();
//Log.d("zpp","SystemUtil.dp2px(getContext(),200) = :"+SystemUtil.dp2px(getContext(),200));
        //Log.d("zpp","onLayout...left = :"+left+",top = :"+top+",right = :"+right+",bottom = :"+bottom+",layoutParams.leftMargin = :"+layoutParams.leftMargin);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zpp","MyView4...onTouchEvent...event = :"+event.getAction());
        if(event.getAction() == MotionEvent.ACTION_CANCEL){

        }
        return super.onTouchEvent(event);
    }
}
