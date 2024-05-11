package lui.base.gui;

import lui.base.data.LPoint;

public interface LLayedCell {

    LCellData getCellData();

    // Interface for get/set size.
    LPoint getCurrentSize();
    void setCurrentSize(int width, int height);

    // Interface for preferred size.
    LPoint getTargetSize();
    void setTargetSize(int width, int height);

    // Interface for minimum size.
    LPoint getRequiredSize();
    void setRequiredSize(int width, int height);

}
