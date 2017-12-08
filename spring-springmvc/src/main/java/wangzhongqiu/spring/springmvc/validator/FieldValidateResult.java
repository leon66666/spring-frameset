package wangzhongqiu.spring.springmvc.validator;

public class FieldValidateResult {

    private String fieldName;
    
    private boolean valid;

    public FieldValidateResult(String fieldName, boolean valid) {
        super();
        this.fieldName = fieldName;
        this.valid = valid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
}
