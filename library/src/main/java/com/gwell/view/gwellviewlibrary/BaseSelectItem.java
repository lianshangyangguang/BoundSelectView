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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseSelectItem that = (BaseSelectItem) o;

        if (mark != that.mark) return false;
        return str != null ? str.equals(that.str) : that.str == null;

    }

    @Override
    public int hashCode() {
        int result = str != null ? str.hashCode() : 0;
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return "BaseSelectItem{" +
                "str='" + str + '\'' +
                ", mark=" + mark +
                '}';
    }
}
