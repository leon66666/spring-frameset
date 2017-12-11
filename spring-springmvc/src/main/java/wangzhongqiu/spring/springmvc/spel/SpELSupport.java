/**
 * 
 * @Titile: SpELProcessor.java
 * 
 * @Description: <br>
 * <br>
 * 
 */
package wangzhongqiu.spring.springmvc.spel;

import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

public interface SpELSupport {

    /**
     * @Title: registerMethod 
     * @Description: Register custom function to SpEL parser context
     * @param context
     * @throws Exception 
     * @return void
     * @throws
     */
    public void registerMethod(StandardEvaluationContext context) throws Exception;
    
    /**
     * @Title: compile 
     * @Description: Parse the SpEL expression
     * @param spel 
     * @param contextKeys
     * @param contextValues
     * @return
     * @throws Exception 
     * @return List<SpELCompileResult>
     * @throws
     */
    public List<SpELCompileResult> compile(String spel, String[] contextKeys, Object[] contextValues) throws Exception;
}
