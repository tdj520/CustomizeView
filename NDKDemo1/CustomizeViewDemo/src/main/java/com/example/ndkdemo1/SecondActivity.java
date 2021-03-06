package com.example.ndkdemo1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import static android.view.View.LAYER_TYPE_HARDWARE;

public class SecondActivity extends Activity {
    private Button btn;
    private ImageView imageView;
    private Button btn1;
    private int transaction;
    private MyView4 myView4;
    private Button btn2;
    private boolean isLike = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        btn = findViewById(R.id.btn);
        imageView = findViewById(R.id.image);
        myView4 = findViewById(R.id.myView4);
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
                //ViewPropertyAnimator viewPropertyAnimator =  imageView.animate().setDuration(1000).translationX(300).alpha(0.5f);

                PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 300);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 100);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0.5f);
                PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("scaleX", 2);
                PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat("scaleY", 2);

                ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(imageView,new PropertyValuesHolder[]{holder1,holder2,holder3,holder4,holder5});
                objectAnimator1.setDuration(2000);
                objectAnimator1.start();

                //设置插值器，插值器在动画中意思是：根据动画时间完成度计算动画完成度
                //viewPropertyAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                //viewPropertyAnimator.setInterpolator(new LinearInterpolator());
                //viewPropertyAnimator.setInterpolator(new CycleInterpolator(1));
                //viewPropertyAnimator.setInterpolator(new BounceInterpolator());
                //viewPropertyAnimator.setInterpolator(new AnticipateInterpolator());
                //viewPropertyAnimator.setInterpolator(new OvershootInterpolator());
                //viewPropertyAnimator.setInterpolator(new AnticipateOvershootInterpolator());
                //viewPropertyAnimator.setInterpolator(new FastOutLinearInInterpolator());
                /*Path interpolatorPath = new Path();
                // 先以「动画完成度 : 时间完成度 = 1 : 1」的速度匀速运行 25%
                interpolatorPath.lineTo(0.1f, 0.5f);
                // 然后瞬间跳跃到 150% 的动画完成度
                interpolatorPath.lineTo(0.1f, 1f);
                // 再匀速倒车，返回到目标点
                interpolatorPath.lineTo(1f, 1);
                PathInterpolator pathInterpolator = new PathInterpolator(interpolatorPath);
                viewPropertyAnimator.setInterpolator(pathInterpolator);*/
                //viewPropertyAnimator.setInterpolator(new LinearOutSlowInInterpolator());
                int arr[] = {};
                int a = arr[10];

            }
        });

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                @SuppressLint("ObjectAnimatorBinding") ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"disX",30);
                //自定义TimeInterpolator插值器,input代表动画完成的时间,范围[0,1]
                objectAnimator.setInterpolator(new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        //Log.d("zpp","input = :"+input);
                        return input * 30;
                    }
                });

                objectAnimator.setDuration(5000 ).start();

                //颜色渐变
                //@SuppressLint("ObjectAnimatorBinding") ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(myView4,"color",0xFFFF0000,0xFF00FF00);
                //求值器，定义动画完成度计算出实际属性值的规则
                //objectAnimator1.setEvaluator(new ArgbEvaluator());

                //PropertyValuesHolder对一系列属性做动画
                /*PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 300);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 100);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0.5f);
                PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("scaleX", 2);
                PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat("scaleY", 2);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(myView4,new PropertyValuesHolder[]{holder1,holder2,holder3,holder4,holder5});
                objectAnimator1.setDuration(2000);
                objectAnimator1.start();*/

                //第一个参数是时间完成度，第二个参数是动画完成度
                /*Keyframe keyframe = Keyframe.ofFloat(0,0);   //时间完成度为0,动画的值为0
                Keyframe keyframe1 = Keyframe.ofFloat(0.7f,300); //时间完成度为0.8,动画的值为300
                Keyframe keyframe2 = Keyframe.ofFloat(0.8f,200);    //时间完成度为1,动画的值为200
                Keyframe keyframe3 = Keyframe.ofFloat(1,300);    //时间完成度为1,动画的值为200

                Keyframe keyframe4 = Keyframe.ofFloat(0,1);   //时间完成度为0,动画的值为0
                Keyframe keyframe5 = Keyframe.ofFloat(0.7f,0.2f); //时间完成度为0.8,动画的值为300
                Keyframe keyframe6 = Keyframe.ofFloat(0.8f,0.5f);    //时间完成度为1,动画的值为200
                Keyframe keyframe7 = Keyframe.ofFloat(1,1);    //时间完成度为1,动画的值为200

                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX",keyframe,keyframe1,keyframe2,keyframe3);
                PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofKeyframe("alpha",keyframe4,keyframe5,keyframe6,keyframe7);

                ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(myView4,propertyValuesHolder,propertyValuesHolder1);
                objectAnimator1.setDuration(2000);
                objectAnimator1.start();*/

                /*
                * 动画执行先后顺序
                * */
                /*ObjectAnimator animator = ObjectAnimator.ofFloat(myView4,"translationX",300);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(myView4,"scaleX",2);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(myView4,"scaleY",2);
                ObjectAnimator animator3 = ObjectAnimator.ofArgb(myView4,"color",0xFFFF0000,0xFF00FF00);
                AnimatorSet animatorSet = new AnimatorSet();
                //按顺序执行动画
                //animatorSet.playSequentially(animator,animator1,animator2,animator3);
                animatorSet.play(animator).with(animator1).with(animator2).with(animator3);
                animatorSet.setDuration(1000);
                animatorSet.start();*/
                //对一个point做动画
                /*@SuppressLint("ObjectAnimatorBinding") ObjectAnimator objectAnimator2 = ObjectAnimator.ofObject(myView4,"position", new PointEvaluator(), new Point(500,0));
                objectAnimator2.setDuration(2000);
                objectAnimator2.start();*/

                /*myView4.setLayerType(LAYER_TYPE_HARDWARE, null);
                ValueAnimator valueAnimator = ValueAnimator.ofArgb(0xFF00FF00);
                ValueAnimator valueAnimator1 = ValueAnimator.ofInt(300);
                valueAnimator1.setDuration(2000);
                valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Log.d("zpp","animation.getAnimatedValue = :"+animation.getAnimatedValue());
                        myView4.setTranslationX((int)animation.getAnimatedValue());
                    }
                });
                valueAnimator1.start();*/
                isLike = !isLike;
                ObjectAnimator objectAnimator1,objectAnimator2,objectAnimator3,objectAnimator5,objectAnimator4;
                if(isLike){
                    objectAnimator1 = ObjectAnimator.ofFloat(myView4,"testScale",1.0f,0.8f,1.0f);
                    objectAnimator2 = ObjectAnimator.ofFloat(myView4,"radius",60);
                    objectAnimator3 = ObjectAnimator.ofFloat(myView4,"circleAlpha",1,0);
                    objectAnimator5 = ObjectAnimator.ofFloat(myView4,"circleAlpha1",0,1);
                    objectAnimator4 = ObjectAnimator.ofInt(myView4,"textDis",20);   //字符串移动距离，从0到20
                }else {
                    objectAnimator1 = ObjectAnimator.ofFloat(myView4,"testScale",1.0f,0.8f,1.0f);
                    objectAnimator2 = ObjectAnimator.ofFloat(myView4,"radius",60);
                    objectAnimator3 = ObjectAnimator.ofFloat(myView4,"circleAlpha",0,1);
                    objectAnimator5 = ObjectAnimator.ofFloat(myView4,"circleAlpha1",1,0);
                    objectAnimator4 = ObjectAnimator.ofInt(myView4,"textDis",20,0);   //字符串移动距离，从20到0
                }


                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator2,objectAnimator3,objectAnimator4,objectAnimator5);
                animatorSet.start();
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));


            }
        });
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //主线程异常拦截
                while (true) {
                    try {
                        Log.d("dongjiao","Looper.loop..........");
                        Looper.loop();//主线程的异常会从这里抛出
                    } catch (Throwable e) {
                        Log.d("dongjiao","线程 = ："+Thread.currentThread()+",异常 = ："+e);
                    }
                }
            }
        });

    }
    //自定义求值器
    class TestEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            //Log.d("dongjiao","TestEvaluator...startValue = :"+startValue+",endValue = :"+endValue+",fraction = :"+fraction);

            return (int) (startValue +endValue*fraction);
        }
    }
    //自定义求值器
    class PointEvaluator implements TypeEvaluator<Point>{
        Point p = new Point();
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            p.set((int) (startValue.x + fraction*endValue.x),(int) (startValue.y + fraction*endValue.y));
            Log.d("dongjiao","fraction*endValue.x = :"+(int)(fraction*endValue.x));
            return p;
        }
    }

}
