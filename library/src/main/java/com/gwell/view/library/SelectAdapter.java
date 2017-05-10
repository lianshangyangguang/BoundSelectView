package com.gwell.view.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xiyingzhu on 2017/5/8.
 */
public class SelectAdapter<T extends BaseSelectItem> extends BaseAdapter {

    private Context context;
    private ArrayList<T> arrayList = new ArrayList<>();
    private T currentT;
    private OnDataSetChangedListener onDataSetChangedListener;
    //Item的高度
    private float itemWidth = 37.5f;
    //Item的宽度
    private float itemHeight = 46f;
    //Item选中状态下字体颜色
    private int selectedColor = Color.rgb(0x52, 0xa0, 0xe0);
    //Item未选中状态下字体颜色
    private int unselectColor = Color.rgb(0xff, 0xff, 0xff);
    //Item的字体大小
    private float itemTextSize = 12f;
    //Item的背景
    private Drawable itemBackgroud = null;

    public SelectAdapter(Context context, ArrayList<T> arrayList, T currentT) {
        this.context = context;
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        this.currentT = currentT;
    }

    public void changeData(ArrayList<T> arrays, T currentT) {
        arrayList.clear();
        arrayList.addAll(arrays);
        this.currentT = currentT;
        SelectAdapter.this.notifyDataSetChanged();
        onDataSetChangedListener.changeCurrentT(currentT);
    }

    public void changeAttes(int itemHeight, int itemWidth, int selectedColor, int unselectColor,
                            float itemTextSize, Drawable itemBackgroud) {
        if (itemHeight != 0) {
            this.itemHeight = itemHeight;
        }
        if (itemWidth != 0) {
            this.itemWidth = itemWidth;
        }
        if (selectedColor != 0) {
            this.selectedColor = selectedColor;
        }
        if (unselectColor != 0) {
            this.unselectColor = unselectColor;
        }
        if (itemTextSize != 0) {
            this.itemTextSize = itemTextSize;
        }
        this.itemBackgroud = itemBackgroud;
        notifyDataSetChanged();
    }

    public void setOnDataSetChangedListener(OnDataSetChangedListener onDataSetChangedListener) {
        this.onDataSetChangedListener = onDataSetChangedListener;
    }

    public interface OnDataSetChangedListener {
        void changeCurrentT(Object currentT);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.library_select_item, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txt.setLayoutParams(new LinearLayout.LayoutParams(SelectView.dip2px(itemWidth), SelectView.dip2px(itemHeight)));
        holder.txt.setText(arrayList.get(position).getStr());
        holder.txt.setTextSize(itemTextSize);
        if (itemBackgroud == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.txt.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.lib_textbg, null));
            } else {
                holder.txt.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.lib_textbg));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.txt.setBackground(itemBackgroud);
            } else {
                holder.txt.setBackgroundDrawable(itemBackgroud);
            }
        }
        if (arrayList.get(position).equals(currentT)) {
            holder.txt.setTextColor(selectedColor);
        } else {
            holder.txt.setTextColor(unselectColor);
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView txt;

        public ViewHolder(View viewRoot) {
            txt = (TextView) viewRoot.findViewById(R.id.txt_item);
        }
    }
}
