package wangzhongqiu.spring.core.exception.base;

public class InvalidPagingException extends BusinessBaseException {

    private static final long serialVersionUID = 8361454453528707254L;

    public InvalidPagingException(Integer start, Integer length) {
        super(String.format("Invalid paging: start %d, length %d", start, length));
    }

}
