package com.gwell.view.library;

import android.graphics.drawable.Drawable;

/**
 * Created by xiyingzhu on 2017/5/9.
 */
public class Attribute {
    private final int itemHeight;
    private final int itemWidth;
    private final int selectedColor;
    private final int unselectColor;
    private final float itemTextSize;
    private final int lineHeight;
    private final Drawable lineDrawable;
    private final Drawable itemBackgroud;
    private final int rootPadding;
    private final int rootViewHight;
    private final float rootTextSize;
    private final int rootTextColor;

    private Attribute(AttributeBuilder attributeBuilder){
        this.itemHeight = attributeBuilder.itemHeight;
        this.selectedColor = attributeBuilder.selectedColor;
        this.unselectColor = attributeBuilder.unselectColor;
        this.itemTextSize = attributeBuilder.itemTextSize;
        this.lineHeight = attributeBuilder.lineHeight;
        this.lineDrawable = attributeBuilder.lineDrawable;
        this.itemBackgroud = attributeBuilder.itemBackgroud;
        this.rootPadding = attributeBuilder.rootPadding;
        this.rootViewHight = attributeBuilder.rootViewHight;
        this.rootTextSize = attributeBuilder.rootTextSize;
        this.rootTextColor = attributeBuilder.rootTextColor;
        this.itemWidth = attributeBuilder.itemWidth;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public int getUnselectColor() {
        return unselectColor;
    }

    public float getItemTextSize() {
        return itemTextSize;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public android.graphics.drawable.Drawable getLineDrawable() {
        return lineDrawable;
    }

    public android.graphics.drawable.Drawable getItemBackgroud() {
        return itemBackgroud;
    }

    public int getRootPadding() {
        return rootPadding;
    }

    public int getRootViewHight() {
        return rootViewHight;
    }

    public float getRootTextSize() {
        return rootTextSize;
    }

    public int getRootTextColor() {
        return rootTextColor;
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public static class AttributeBuilder {
        private int itemHeight;
        private int itemWidth;
        private int selectedColor;
        private int unselectColor;
        private float itemTextSize;
        private int lineHeight;
        private Drawable lineDrawable;
        private Drawable itemBackgroud;
        private int rootPadding;
        private int rootViewHight;
        private float rootTextSize;
        private int rootTextColor;

        public AttributeBuilder itemHeight(int itemHeight) {
            this.itemHeight = itemHeight;
            return this;
        }
        public AttributeBuilder itemWidth(int itemWidth) {
            this.itemWidth = itemWidth;
            return this;
        }

        public AttributeBuilder selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        public AttributeBuilder unselectColor(int unselectColor) {
            this.unselectColor = unselectColor;
            return this;
        }

        public AttributeBuilder itemTextSize(float itemTextSize) {
            this.itemTextSize = itemTextSize;
            return this;
        }

        public AttributeBuilder rootPadding(int rootPadding) {
            this.rootPadding = rootPadding;
            return this;
        }

        public AttributeBuilder rootViewHight(int rootViewHight) {
            this.rootViewHight = rootViewHight;
            return this;
        }

        public AttributeBuilder lineDrawable(Drawable lineDrawable) {
            this.lineDrawable = lineDrawable;
            return this;
        }

        public AttributeBuilder itemBackgroud(Drawable itemBackgroud) {
            this.itemBackgroud = itemBackgroud;
            return this;
        }

        public AttributeBuilder lineHeight(int lineHeight) {
            this.lineHeight = lineHeight;
            return this;
        }

        public AttributeBuilder rootTextColor(int rootTextColor) {
            this.rootTextColor = rootTextColor;
            return this;
        }

        public AttributeBuilder rootTextSize(float rootTextSize) {
            this.rootTextSize = rootTextSize;
            return this;
        }
        public Attribute build(){
            return new Attribute(this);
        }
    }
}
