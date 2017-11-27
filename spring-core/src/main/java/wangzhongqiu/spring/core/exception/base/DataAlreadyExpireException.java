package wangzhongqiu.spring.core.exception.base;

public class DataAlreadyExpireException extends BusinessBaseException {

    private static final long serialVersionUID = -4089286652078392149L;

    public DataAlreadyExpireException(String entityName, Integer id) {
        super(String.format("Data %s for %d has already expired", entityName, id));
    }

    public DataAlreadyExpireException(String entityName) {
        super(String.format("Data %s has already expired", entityName));
    }

    public DataAlreadyExpireException(String entityName, String cause) {
        super(String.format("Data %s has already expired, because %s", entityName, cause));
    }
}
