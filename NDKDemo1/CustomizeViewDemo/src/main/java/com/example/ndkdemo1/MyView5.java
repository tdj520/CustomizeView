package com.example.ndkdemo1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyView5 extends ViewGroup {
    public MyView5(Context context) {
        super(context);
    }

    public MyView5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int parentWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int parentHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d("zpp","sizeWidth= :"+sizeWidth+",sizeHeight = :"+sizeHeight);
        int count = getChildCount();
        int maxWidth = sizeWidth;
        int maxHeight = sizeHeight;
        int childWidth = 0;
        int childHeight = 0;
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            Log.d("zpp","child= :"+child+",layoutParams.width = :"+lp.width+",layoutParams.height = :"+lp.height);
            int childWidthMsc = 0;
            int childHeightMsc = 0;
            if(lp.width == LayoutParams.WRAP_CONTENT){
               // maxWidth = getHeight();
                if(parentWidthMode == MeasureSpec.EXACTLY || parentWidthMode == MeasureSpec.AT_MOST){
                    childWidthMsc = MeasureSpec.makeMeasureSpec(sizeWidth,MeasureSpec.AT_MOST);
                }else {
                    childWidthMsc = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                }

                if(parentHeightMode == MeasureSpec.EXACTLY || parentHeightMode == MeasureSpec.AT_MOST){
                    childHeightMsc = MeasureSpec.makeMeasureSpec(sizeHeight,MeasureSpec.AT_MOST);
                }else {
                    childHeightMsc = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                }
            }else if(lp.width == LayoutParams.MATCH_PARENT){
                  if(parentWidthMode == MeasureSpec.EXACTLY || parentWidthMode == MeasureSpec.AT_MOST){
                      childWidthMsc = MeasureSpec.makeMeasureSpec(sizeWidth,MeasureSpec.EXACTLY);
                  }else {
                      childWidthMsc = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                  }

                if(parentHeightMode == MeasureSpec.EXACTLY || parentHeightMode == MeasureSpec.AT_MOST){
                    childHeightMsc = MeasureSpec.makeMeasureSpec(sizeHeight,MeasureSpec.EXACTLY);
                }else {
                    childHeightMsc = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                }

            }

//            child.layout(SystemUtil.dp2px(getContext(),100),0,child.getMeasuredWidth(),child.getMeasuredHeight());
            //Log.d("zpp","child= :"+child+",width = :"+MeasureSpec.getSize(childWidthMsc)+",height = :"+MeasureSpec.getSize(childHeightMsc)+",widthMode = ："+MeasureSpec.getMode(childWidthMsc)+",heightMode = :"+MeasureSpec.getMode(childHeightMsc));
            child.measure(childWidthMsc,childHeightMsc);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
        }
       if(parentWidthMode == MeasureSpec.AT_MOST){
           maxWidth = childWidth;
       }
       if(parentHeightMode == MeasureSpec.AT_MOST){
           maxHeight = childHeight;
       }
        Log.d("zpp","onMeasure....maxWidth = :"+maxWidth+",maxHeight = :"+maxHeight);

        setMeasuredDimension(resolveSize(maxWidth,widthMeasureSpec),resolveSize(maxHeight,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        Log.d("zpp","MyView5..onLayout..l = :"+l+",t = :"+t+",r = :"+r+",b = :"+b+",count = :"+count);
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Log.d("zpp","MyView5..onLayout...child = :"+child+",child.getMeasuredWidth() = :"+child.getMeasuredWidth()+",child.getMeasuredHeight() = :"+child.getMeasuredHeight());
            child.layout(SystemUtil.dp2px(getContext(),0),0,child.getMeasuredWidth()+SystemUtil.dp2px(getContext(),0),child.getMeasuredHeight());
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("zpp","MyView5...onInterceptTouchEvent...event = :"+ev.getAction());
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zpp","MyView5...onTouchEvent...event = :"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("zpp","MyView5...dispatchTouchEvent...event = :"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
