package com.gwell.view.gwellviewlibrary;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xiyingzhu on 2017/4/12.
 */
public class BoundSelectView<T extends BaseSelectItem> extends ViewGroup {
    private final static float WITH_DEFAULT=37.5f;
    private final static float LINE_H_DEFAULT=0.5f;
    private Context context;
    private View child;
    public int subCount = 3;
    private int isPopUp = 0;
    private TextView tx, root;
    private ItemOnClickListener itemOnClickListener;
    private ArrayList views = new ArrayList();
    private ArrayList<TextView> textViews = new ArrayList();
    private ArrayList<T> names=new ArrayList<>();
    private String rootName = "root";
    private int Hight=0;
    private int RootPadding=5;
    private int ChildHigh=46;
    private int With=0;
    private int RootViewHight=19;
    private int line_h=0;
    private T currentT;

    public BoundSelectView(final Context context) {
        this(context,null,0);

    }

    public BoundSelectView(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BoundUpView);

        subCount = ta.getInteger(R.styleable.BoundUpView_subCount, 1);
        ta.recycle();

        RootPadding=dip2px(RootPadding);
        ChildHigh=dip2px(ChildHigh);
        With=dip2px(WITH_DEFAULT);
        RootViewHight=dip2px(RootViewHight);
        line_h=dip2px(LINE_H_DEFAULT);
    }

    private void initData(){
        views.clear();
        for (int i = 0; i < subCount; i++) {
            tx = new TextView(context);
            final int j = i;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tx.setBackground(getResources().getDrawable(R.drawable.lib_textbg));
            } else {
                tx.setBackgroundDrawable(getResources().getDrawable(R.drawable.lib_textbg));
            }
            tx.setTextSize(10);
            tx.setWidth(dip2px(37.5f));
            tx.setHeight(dip2px(46));
            tx.setVisibility(GONE);
            tx.setTextSize(12);
            tx.setTextColor(Color.WHITE);

            if (names != null && names.size() != 0) {
                try {
                    tx.setText(names.get(j).getStr());
                    if (names.get(j).getStr().equals(rootName)) {
                        tx.setTextColor(getResources().getColor(R.color.libTextBlue));
                    }else{
                        tx.setTextColor(Color.WHITE);
                    }
                } catch (Exception e) {
                    Log.d("zxy", "onClick: Exception");
                }

            }
            tx.setGravity(Gravity.CENTER);
            views.add(tx);
            textViews.add(tx);
            tx.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPopUp == 1) {
                        hide();
                        if(names!=null&&names.size()>0){
                            T tempT=names.get(j);
                            if(v instanceof TextView){
                                rootName=((TextView)v).getText().toString();
                                root.setText(rootName);
                            }
                            if (itemOnClickListener != null) {
                                itemOnClickListener.onItemClick(tempT, v,currentT.equals(tempT));
                            }
                            currentT=tempT;
                        }
                    }
                }
            });
            addView(tx);
            if (i != subCount - 1) {
                View lineView = new View(context);
                lineView.setBackgroundColor(Color.WHITE);
                lineView.setVisibility(GONE);
                views.add(lineView);
                addView(lineView);
            }
        }
        root = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(37.5f), dip2px(19));
        root.setLayoutParams(params);
        root.setText(rootName);
        root.setTextSize(11);
        root.setTextColor(Color.WHITE);
        root.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            root.setBackground(getResources().getDrawable(R.drawable.lib_rootbtn));
        } else {
            root.setBackgroundDrawable(getResources().getDrawable(R.drawable.lib_rootbtn));
        }
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPopUp == 0) {
                    popUp(v);
                    isPopUp = 1;
                } else {
                    hide();

                }
            }
        });
        addView(root);
    }

    public BoundSelectView(Context context, AttributeSet attrs, int defStyle) {
        super( context,attrs, defStyle);

    }

    public void hide() {
        if (isPopUp == 1) {
            for (int i = 0; i < views.size(); i++) {
                View txt = (View) views.get(i);
                ObjectAnimator animator = ObjectAnimator.ofFloat(txt, "alpha", 1, 0);
                animator.setDuration(100);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
                isPopUp = 0;
            }
        }
    }

    private void popUp(View v) {
        for (int i = 0; i < views.size(); i++) {
            View txt = (View) views.get(i);
            txt.setVisibility(VISIBLE);
            if(txt instanceof TextView){
                changeTextColor((TextView) txt);
            }
            ObjectAnimator animator = ObjectAnimator.ofFloat(txt, "alpha", 0, 1);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(100);
            animator.start();
        }
        bringToFront();
    }

    private void changeTextColor(TextView tx){
        if (tx.getText().equals(root.getText())) {
            tx.setTextColor(getResources().getColor(R.color.libTextBlue));
        }else{
            tx.setTextColor(Color.WHITE);
        }
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
            mWidth = dip2px(dip2px(37.5f));
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = dip2px(dip2px(46.5f) * (subCount) + dip2px(19) + dip2px(5));
        }
        Hight=mHeight;
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int height = 0;
//        for (int i = 0; i < getChildCount() - 1; i++) {
//
//            child = getChildAt(i);
//            if (i % 2 != 0) {
//                child.layout(0, height, initWidth, height + subLineHeight);
//                height += subLineHeight;
//            } else {
//                child.layout(0, height, initWidth, height + subHeight);
//                height += subHeight;
//
//            }
//        }
//        child = getChildAt(getChildCount() - 1);
//        child.layout(0, (subHeight + subLineHeight) * subCount + dip2px(5), initWidth, (subHeight + subLineHeight) * subCount + dip2px(19) + dip2px(5));
        //优先铺平下面的空间
        int childCount=getChildCount();
        int hightTemp=Hight;
        for(int k=childCount-1;k>=0;k--){
            child=getChildAt(k);
            if(k==getChildCount() - 1){
                //rootview
                child.layout(0,Hight-RootViewHight,With,Hight);
                hightTemp-=RootViewHight;
                hightTemp-=RootPadding;
            }else{
                //nameview
                if(k%2==0){
                    //文本
                    child.layout(0,hightTemp-ChildHigh,With,hightTemp);
                    hightTemp-=ChildHigh;
                }else{
                    //线
                    child.layout(0,hightTemp-line_h,With,hightTemp);
                    hightTemp-=line_h;
                }
            }
        }
    }

    public  interface ItemOnClickListener<T extends BaseSelectItem>{
        void onItemClick(T t, View v,boolean isCurentItem);
    }

    public void setBoundButton(ItemOnClickListener itemOnClickListener, ArrayList<T> names, String rootName) {
        this.itemOnClickListener = itemOnClickListener;
        clearViews();
        if (names != null && names.size() != 0) {

            subCount = names.size();
            //由数据决定显示的条目数
            initData();
            for (int i = 0; i < subCount; i++) {
                textViews.get(i).setText(names.get(i).getStr());
                this.names.add(names.get(i));
            }
        }
        if (!TextUtils.isEmpty(rootName)) {
            this.rootName = rootName;
            root.setText(rootName);

            for (int i = 0; i < subCount; i++) {
                if (names != null && names.size() != 0) {
                    try {
                        if (names.get(i).getStr().equals(rootName)) {
                            textViews.get(i).setTextColor(getResources().getColor(R.color.libTextBlue));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private void clearViews(){
        if(textViews!=null){
            textViews.clear();
        }
        if(names!=null){
            names.clear();
        }
        this.removeAllViews();
    }

    private   int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void getCurrentItem(){

    }

}
