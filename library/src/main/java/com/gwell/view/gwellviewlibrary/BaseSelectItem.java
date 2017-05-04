package com.gwell.view.gwellviewlibrary;

/**
 * Created by dansesshou on 17/5/4.
 */

public class BaseSelectItem {
    private String str;
    private int mark;

    public BaseSelectItem() {
    }

    public BaseSelectItem(String str, int mark) {
        this.str = str;
        this.mark = mark;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "BaseSelectItem{" +
                "str='" + str + '\'' +
                ", mark=" + mark +
                '}';
    }
}
