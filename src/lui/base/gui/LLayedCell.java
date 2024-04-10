package lui.base.gui;

import lui.base.data.LPoint;

public interface LLayedCell {

    LLayoutData getCellData();

    LPoint getCurrentSize();

    LPoint getTargetSize();

}
