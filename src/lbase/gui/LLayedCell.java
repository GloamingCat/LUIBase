package lbase.gui;

import lbase.data.LPoint;

public interface LLayedCell {

    LLayoutData getCellData();

    LPoint getCurrentSize();

    LPoint getTargetSize();

}
