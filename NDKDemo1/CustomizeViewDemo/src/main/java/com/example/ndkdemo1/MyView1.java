package com.example.ndkdemo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyView1 extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBitmap1;
    private int dis;
    private Camera camera;
    public MyView1(Context context) {
        this(context,null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.test);
        mBitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher);
         camera = new Camera();
    }
public void setDis(int dis){
        this.dis = dis;
        invalidate();
}
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //camera以原点坐标为中心
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        camera.save();
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        camera.rotateX(dis);
        //将canvas移到camera中心点
        canvas.translate(mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        //用完之后将canvas移到回来
        canvas.translate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);

        canvas.drawBitmap(mBitmap,0,0,mPaint);

        mPaint.setXfermode(xfermode);



        camera.restore();
        canvas.drawBitmap(mBitmap1,0,0,mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
        Log.d("zpp","---------------------------------------------------");
        /*PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 10);
        mPaint.setPathEffect(pathEffect);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 50, 100, mPaint);*/
        mPaint.setShadowLayer(10, 0, 0, Color.RED);
        canvas.drawText("hello world", 200, 150, mPaint);

        //mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.6f, 8, 10));
        //范围裁剪
        /*canvas.save();
        canvas.clipRect(200,0,300,100);
        canvas.drawBitmap(mBitmap,200,0,mPaint);
        canvas.restore();*/

        canvas.save();
        /*Matrix matrix = new Matrix();//矩阵
        Path path = new Path();
        path.addCircle(400,100,50, Path.Direction.CCW);
        //canvas.clipPath(path);
       // matrix.preRotate(dis); //利用矩阵旋转
        matrix.postTranslate(dis,0);//利用矩阵平移
        canvas.concat(matrix);*/
        //canvas.skew(0, 0.5f);//错切

        camera.save(); // 保存 Camera 的状态
        //camera.rotateY(dis); // 旋转 Camera 的三维空间
        camera.rotateX(dis);
        //camera.translate(200,0,0);
        canvas.translate(mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        camera.setLocation(0,0,-3);
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);
        camera.restore(); // 恢复 Camera 的状态*/
        canvas.drawBitmap(mBitmap,200,-0,mPaint);
        canvas.restore();

    }
}
