package lui.base.gui;

import lui.base.data.LPoint;

public interface LLayedCell {

    LCellData getCellData();

    // Interface for get/set size.
    LPoint getCurrentSize();

    // Interface for preferred size.
    LPoint getTargetSize();

    // Interface for minimum size.
    LPoint getRequiredSize();

}
