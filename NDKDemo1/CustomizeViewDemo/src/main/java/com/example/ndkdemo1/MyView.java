package com.example.ndkdemo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyView extends View {
    private Paint mPaint;
    private int r;
    private Bitmap mBitmap;
    private Bitmap mBitmap1;
    private Context mContext;
    Path path = new Path(); // 初始化 Path 对象
    private int color = 0x000000;

    {
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        /*path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);*/
        //path.addCircle(100,100,100, Path.Direction.CCW);
        //path.moveTo(100,0);
        //path.lineTo(200, 100);
        //path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
        path.addCircle(110,120,100, Path.Direction.CW);
        path.addCircle(200,200,100, Path.Direction.CW);
        //path.setFillType(Path.FillType.EVEN_ODD);  //填充图形内部，奇数填充
        //path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        //path.setFillType(Path.FillType.INVERSE_WINDING);
        path.setFillType(Path.FillType.WINDING);
    }
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        mBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

    }
    public void setColorFilter(int color){
        this.color = color;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //canvas.drawCircle(150, 300, 100, mPaint);
        //线性着色器
        Shader shader = new LinearGradient(0, 250, 100, 350, Color.parseColor("#987423"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
        //辐射渐变
        /*Shader shader1 = new RadialGradient(50, 100, 20, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        //mPaint.setColor(Color.parseColor("#987423"));
        canvas.drawPath(path,mPaint);

        mPaint.setShader(shader1);
        //mPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawRect(0,250,100,350,mPaint);*/

        //图片着色器
        Shader shader2 = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Shader shader3 = new BitmapShader(mBitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        ComposeShader shader4 = new ComposeShader(shader2,shader3,PorterDuff.Mode.DST_OUT);
        mPaint.setShader(shader4);
        mPaint.setColorFilter(new LightingColorFilter(color,0x000000));

        //PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC);
        //mPaint.setColorFilter(porterDuffColorFilter);

        canvas.drawCircle(240, 240, 240, mPaint);

        //PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(0xff0000,PorterDuff.Mode.SCREEN);

        //mPaint.setColorFilter(porterDuffColorFilter);
        //canvas.drawRect(100,500,400,600,mPaint);
        //canvas.drawBitmap(mBitmap,100,200,mPaint);
    }
    /*
      LightingColorFilter矩阵用法，LightingColorFilter接收两个参数，mul和add
      R' = R * mul.R / 0xff + add.R
      G' = G * mul.G / 0xff + add.G
      B' = B * mul.B / 0xff + add.B

      例如mul为0xff0000,add为0x000000

      R' = R * 0xff / 0xff + 0x00
      G' = G * 0x00 / 0xff + 0x00
      B' = B * 0x00 / 0xff + 0x00
    */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int heightMode =  MeasureSpec.getMode(heightMeasureSpec);
      int width = MeasureSpec.getSize(widthMeasureSpec);
      int height = MeasureSpec.getSize(heightMeasureSpec);
      if(widthMode == MeasureSpec.AT_MOST || height == MeasureSpec.AT_MOST){
          if(width >= height){
              width = height;
          }
          if(height >= width){
              height = width;
          }
      }
      setMeasuredDimension(width,height);
    }
}
