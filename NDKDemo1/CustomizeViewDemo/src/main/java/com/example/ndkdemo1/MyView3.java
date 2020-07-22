package com.example.ndkdemo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyView3 extends ImageView {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBitmap1;
    private int dis;
    private Camera camera;
    public MyView3(Context context) {
        this(context,null);
    }

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.test);
        mBitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher);
        camera = new Camera();
        setImageBitmap(mBitmap);
    }
public void setDis(int dis){
        this.dis = dis;
        invalidate();
}
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        int weidth = mBitmap.getWidth();
        int heght = mBitmap.getHeight();
        canvas.drawText("尺寸："+weidth+","+heght,0,20,mPaint);
    }
}
