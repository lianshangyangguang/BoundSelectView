package com.gwell.view.gwellviewlibrary;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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
public class BoundSelectView extends ViewGroup {

    private Context context;
    private View child;
    public static int subCount = 3;
    private int isPopUp = 0;
    private TextView tx, root;
    private ItemOnClickListener itemOnClickListener;
    private ArrayList views = new ArrayList();
    private ArrayList<TextView> textViews = new ArrayList();
    private ArrayList<String> names;
    private String rootName = "root";

    public BoundSelectView(final Context context) {
        this(context, null, 0);

    }

    public BoundSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BoundUpView);

        subCount = ta.getInteger(R.styleable.BoundUpView_subCount, 1);
        String name1 = ta.getString(R.styleable.BoundUpView_subName1);
        String name2 = ta.getString(R.styleable.BoundUpView_subName2);
        String name3 = ta.getString(R.styleable.BoundUpView_subName3);
        String name4 = ta.getString(R.styleable.BoundUpView_subName4);
        String name5 = ta.getString(R.styleable.BoundUpView_subName5);
        rootName = ta.getString(R.styleable.BoundUpView_rootName);
        names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        ta.recycle();

        views.clear();

        for (int i = 0; i < subCount; i++) {
            tx = new TextView(context);
            final int j = i;
            tx.setBackground(getResources().getDrawable(R.drawable.lib_textbg));
            tx.setTextSize(10);
            tx.setWidth(Utils.dip2px(context, 37.5f));
            tx.setHeight(Utils.dip2px(context, 46));
            tx.setVisibility(GONE);
            tx.setTextSize(12);
            tx.setTextColor(Color.WHITE);

            if (names != null && names.size() != 0) {
                try {
                    tx.setText(names.get(j));
                    if (names.get(j).equals(rootName)) {
                        tx.setTextColor(getResources().getColor(R.color.libTextBlue));
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
                    root.setText("");
                    if (isPopUp == 1) {
                        hide();
                        if (names != null && names.size() != 0) {
                            try {
                                for (int k = 0; k < textViews.size(); k++) {
                                    if (k == j) {
                                        textViews.get(k).setTextColor(getResources().getColor(R.color.libTextBlue));
                                    } else
                                        textViews.get(k).setTextColor(Color.WHITE);
                                }
                                root.setText(names.get(j));
                            } catch (Exception e) {
                                Log.d("zxy", "onClick: Exception");
                            }
                        }
                        if (itemOnClickListener != null) {
                            try {
                                itemOnClickListener.onItemClick(j, tx);
                            } catch (Exception e) {
                                Log.d("zxy", "onClick: Exception");
                            }
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Utils.dip2px(context, 37.5f), Utils.dip2px(context, 19));
        root.setLayoutParams(params);
        root.setText(rootName);
        root.setTextSize(12);
        root.setTextColor(Color.WHITE);
        root.setGravity(Gravity.CENTER);
        root.setBackground(getResources().getDrawable(R.drawable.lib_rootbtn));
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
        super(context, attrs, defStyle);

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
            ObjectAnimator animator = ObjectAnimator.ofFloat(txt, "alpha", 0, 1);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(100);
            animator.start();
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
            mWidth = Utils.dip2px(context, Utils.dip2px(context, 37.5f));
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = Utils.dip2px(context, Utils.dip2px(context, 46.5f) * (subCount) + Utils.dip2px(context, 19) + Utils.dip2px(context, 5));
        }
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int subHeight = Utils.dip2px(context, 46);
        int subLineHeight = Utils.dip2px(context, 0.5f);
        int initWidth = Utils.dip2px(context, 37.5f);

        int height = 0;
        for (int i = 0; i < getChildCount() - 1; i++) {

            child = getChildAt(i);
            if (i % 2 != 0) {
                child.layout(0, height, initWidth, height + subLineHeight);
                height += subLineHeight;
            } else {
                child.layout(0, height, initWidth, height + subHeight);
                height += subHeight;

            }
        }
        child = getChildAt(getChildCount() - 1);
        child.layout(0, (subHeight + subLineHeight) * subCount + Utils.dip2px(context, 5), initWidth, (subHeight + subLineHeight) * subCount + Utils.dip2px(context, 19) + Utils.dip2px(context, 5));
    }

    public interface ItemOnClickListener {
        void onItemClick(int i, View v);
    }

    public void setBoundButton(ItemOnClickListener itemOnClickListener, ArrayList<String> names, String rootName) {
        this.itemOnClickListener = itemOnClickListener;
//        if (subCount >mysubCount){
//            for (int i = (mysubCount - 1)*2-1;i<(subCount - 1)*2+1;i++){
//                removeViewAt(i);
//            }
//        }else if (subCount<mysubCount){
//
//        }
        if (names != null && names.size() != 0) {

            int length = subCount > names.size() ? names.size() : subCount;
            for (int i = 0; i < length; i++) {
                textViews.get(i).setText(names.get(i));
                this.names.set(i, names.get(i));
            }

        }
        if (!TextUtils.isEmpty(rootName)) {
            this.rootName = rootName;
            root.setText(rootName);

            for (int i = 0; i < subCount; i++) {
                if (names != null && names.size() != 0) {
                    try {
                        if (names.get(i).equals(rootName)) {
                            textViews.get(i).setTextColor(getResources().getColor(R.color.libTextBlue));
                        }
                    } catch (Exception e) {
                        Log.d("zxy", "onClick: Exception");
                    }
                }
            }
        }
    }


}