package wangzhongqiu.spring.core.exception.base;

import java.util.Date;

public class InvalidTimeRangeException extends BusinessBaseException {

    private static final long serialVersionUID = -3314848513057568418L;

    public InvalidTimeRangeException(Date begin, Date end) {
        super(String.format("Invalid time range: begin %d, end %d", String.valueOf(begin), String.valueOf(end)));
    }

}
