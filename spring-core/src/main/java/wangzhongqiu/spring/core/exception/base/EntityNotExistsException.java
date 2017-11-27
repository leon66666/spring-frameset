package wangzhongqiu.spring.core.exception.base;

public class EntityNotExistsException extends BusinessBaseException {

    private static final long serialVersionUID = -4089286652078392149L;
    
    private String entityName;

    public EntityNotExistsException(String entityName, Integer id) {
        super(String.format("Entity %s for %d doesn't exist", entityName, id));
        this.entityName = entityName;
    }

    public EntityNotExistsException(String entityName, String entityNameFK, Integer id) {
        super(String.format("Entity %s for %s-%d doesn't exist", entityName, entityNameFK, id));
        this.entityName = entityName;
    }

    public EntityNotExistsException(String entityName, String id) {
        super(String.format("Entity %s for %s doesn't exist", entityName, id));
        this.entityName = entityName;
    }
    
    public String getEntityName() {
        return entityName;
    }

}
