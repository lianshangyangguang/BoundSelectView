package com.gwell.view.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xiyingzhu on 2017/5/8.
 */
public class SelectView<T extends BaseSelectItem> extends ViewGroup {

    private static Context context;
    private TextView root;
    private View child;
    private OnItemClickListener onItemClickListener;
    private ArrayList<T> arrays = new ArrayList<>();
    private T currentT;
    private SelectAdapter adapter;
    private ListView listView;
    private int mWidth;
    private int mHeight;

    //view布局的宽度
    private final static float WITH_DEFAULT = 37.5f;
    //分割线的高度
    private float lineHeight = 0.5f;
    //分割线图片
    private Drawable lineDrawable = new ColorDrawable(Color.WHITE);
    //两个控件部分的上下距离
    private int rootPadding = 5;
    //底部控件的高度
    private int rootViewHight = 19;
    //底部控件字体大小
    private float rootTextSize = 11f;
    //底部控件字体颜色
    private int rootTextColor = Color.rgb(0xff,0xff,0xff);

    public SelectView(Context context) {

        this(context, null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }
    public SelectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.context = context;
        listView = new ListView(context);
        listView.setDivider(lineDrawable);
        listView.setDividerHeight(dip2px(lineHeight));
        adapter = new SelectAdapter(context, arrays, currentT);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrays != null) {
                    adapter.changeData(arrays, arrays.get(position));
                    hide();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(arrays.get(position), view, arrays.get(position).equals(currentT));
                    }
                    currentT = arrays.get(position);
                }
            }
        });
        listView.setAdapter(adapter);

        addView(listView);
        addRootView();
    }

    private void addRootView() {
        root = new TextView(context);
        if (currentT != null)
            root.setText(currentT.getStr());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(WITH_DEFAULT), dip2px(rootViewHight));
        root.setLayoutParams(params);
        root.setTextSize(rootTextSize);
        root.setTextColor(rootTextColor);
        root.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            root.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.lib_rootbtn, null));
        } else {
            root.setBackgroundDrawable(getResources().getDrawable(R.drawable.lib_rootbtn));
        }
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility() == View.VISIBLE) {
                    hide();
                } else
                    show();
            }
        });
        adapter.setOnDataSetChangedListener(new SelectAdapter.OnDataSetChangedListener() {
            @Override
            public void changeCurrentT(Object currentT) {
                BaseSelectItem current = (BaseSelectItem) currentT;
                root.setText(current.getStr());
            }
        });
        addView(root);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = dip2px (WITH_DEFAULT);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            child = getChildAt(0);
            int childMeasureHeight = child.getMeasuredHeight();
            mHeight = childMeasureHeight + dip2px(rootPadding) + dip2px(rootViewHight);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        child = getChildAt(0);
        int childMeasureHeight = child.getMeasuredHeight();
        child.layout(0, 0, dip2px(WITH_DEFAULT), childMeasureHeight);
        child = getChildAt(1);
        child.layout(0, childMeasureHeight + dip2px(rootPadding), dip2px(WITH_DEFAULT),
                childMeasureHeight + dip2px(rootPadding) + dip2px(rootViewHight));
    }

    public static int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public interface OnItemClickListener<T extends BaseSelectItem> {
        void onItemClick(T t, View v, boolean isCurentItem);
    }


    public void hide() {
        if (listView.getVisibility() == View.VISIBLE) {
            listView.setVisibility(View.GONE);
        }
    }

    public void show() {
        if (listView.getVisibility() == View.GONE) {
            listView.setVisibility(View.VISIBLE);
        }
    }

    public T getCurrentT() {
        return currentT;
    }

    public void setAttrs(Attribute attribute){
        if (attribute.getRootPadding() != 0){
            this.rootPadding = attribute.getRootPadding();
        }
        if (attribute.getRootViewHight() != 0){
            this.rootViewHight = attribute.getRootViewHight();
        }
        if (attribute.getRootTextSize() != 0){
            this.rootTextSize = attribute.getRootTextSize();
            root.setTextSize(rootTextSize);
        }
        if (attribute.getRootTextColor() != 0 ){
            this.rootTextColor = attribute.getRootTextColor();
            root.setTextColor(rootTextColor);
        }
        if (attribute.getLineHeight() != 0){
            this.lineHeight = attribute.getLineHeight() ;
            listView.setDividerHeight(dip2px(lineHeight));
        }
        if (attribute.getLineDrawable() != null){
            listView.setDivider(attribute.getLineDrawable());
        }
        adapter.changeAttes(attribute.getItemHeight(),attribute.getItemWidth(),
                attribute.getSelectedColor(),attribute.getUnselectColor() ,
                attribute.getItemTextSize(),attribute.getItemBackgroud()
                );
    }

    public void setSelectView(OnItemClickListener onItemClickListener2, ArrayList<T> arrays2, final T currentT2) {
        onItemClickListener = onItemClickListener2;
        this.currentT = currentT2;
        root.setText(currentT.getStr());
        arrays.clear();
        arrays.addAll(arrays2);
        adapter.changeData(arrays, currentT);
    }
}
