package de.ergodirekt.drag.utils.fileicon;

import de.ergodirekt.drag.utils.DragException;

public class DateiExistiertNichtException extends DragException {
    public DateiExistiertNichtException(String errormsg) {
        super(errormsg);
    }
}
