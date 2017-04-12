package com.gwell.view.gwellviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * Created by xiyingzhu on 2017/4/12.
 */
public class BoundUpButton extends ViewGroup {

        private Context context;
        private View child;
        private int subCount = 3;
        private int isPopUp = 0;

        public BoundUpButton(final Context context) {
           this(context,null,0);

        }

        public BoundUpButton(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public BoundUpButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            this.context = context;

            TextView tx = new TextView(context);
//            tx.setPadding(80,10,10,10);
            tx.setTextSize(10);
            tx.setGravity(Gravity.CENTER_HORIZONTAL);
            tx.setText("root");
            tx.setBackground(getResources().getDrawable(R.drawable.rootbtn));
            tx.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPopUp == 0){
                        popUp(v);
                        isPopUp = 1;
                    }else {
                        comeBack(v);
                        isPopUp = 0;
                    }
                }
            });
            addView(tx);
            for (int i = 0;i<subCount;i++){
                tx.setTextSize(10);
                tx.setGravity(Gravity.CENTER_HORIZONTAL);
                tx.setText(i);
                tx.setBackground(getResources().getDrawable(R.drawable.sd_backgroud));
                addView(tx);
            }
        }

    private void comeBack(View v) {
        TranslateAnimation animation = new TranslateAnimation(200,200,0,0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        v.startAnimation(animation);
    }

    private void popUp(View v) {
        TranslateAnimation animation = new TranslateAnimation(0,0,200,200);
        animation.setDuration(500);
        animation.setFillAfter(true);
        v.startAnimation(animation);

    }

    @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int mWidth = 0;
            int mHeight = 0;
            if (widthMode == MeasureSpec.EXACTLY) {
                mWidth = widthSize;
            } else {
                mWidth = Utils.dip2px(context, 120);
            }


            if (heightMode == MeasureSpec.EXACTLY) {
                mHeight = heightSize;
            } else {
                mHeight = Utils.dip2px(context, 80);
            }
            setMeasuredDimension(mWidth, mHeight);
        }


        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            int initHeight = Utils.dip2px(context,22);
            int initWidth = Utils.dip2px(context,40.5f);
            child = getChildAt(0);
            child.layout(0, 0, initWidth,initHeight);
            for (int i =1 ;i< getChildCount();i++){
                initHeight += Utils.dip2px(context,27);
                child = getChildAt(i);
                child.layout(0, initHeight, initWidth,initHeight+ Utils.dip2px(context,22));
            }
        }
    
    

    }

