package lbase.gui;

import lbase.LFlags;
import lbase.data.LPoint;

public class LLayoutData {

    protected int hSpread = 1, vSpread = 1;
    protected boolean hExpand = false, vExpand = false;
    protected int alignment = LFlags.LEFT | LFlags.TOP;
    protected int minWidth = 0, minHeight = 0;

    protected int width = -1, height = -1;

    public void setSpread(int cols, int rows) {
        hSpread = cols;
        vSpread = rows;
    }

    public void setExpand(boolean h, boolean v) {
        hExpand = h;
        vExpand = v;
    }

    public void setAlignment(int a) {
        alignment = a;
    }

    public void setMinimumSize(LPoint size) {
       setMinimumSize(size.x, size.y);
    }

    public void setMinimumSize(int w, int h) {
        minWidth = w;
        minHeight = h;
    }

    public void setPreferredSize(LPoint size) {
        setPreferredSize(size.x, size.y);
    }

    public void setPreferredSize(int w, int h) {
        width = w;
        height = h;
    }

}
