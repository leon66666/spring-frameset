/**
 * 
 * @Titile: SpELProcessor.java
 * 
 * @Description: SpEL表达式编译处理类 <br>
 * <br>
 * 
 */
package wangzhongqiu.spring.springmvc.spel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class RRDExpressionCompiler implements SpELSupport{
    
    private List<Method> customFunctions;
    
    public void addFunction(Method function){
        this.customFunctions.add(function);
    }
    
    private static final ParserContext parserContext = new ParserContext() {
        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        public String getExpressionPrefix() {
            return "{";
        }

        @Override
        public String getExpressionSuffix() {
            return "}";
        }
    };
    
    @Override
    public void registerMethod(StandardEvaluationContext context) throws Exception{
        
        Method isNotBlank = StringUtils.class.getDeclaredMethod("isNotBlank", CharSequence.class);
        Method isBlank = StringUtils.class.getDeclaredMethod("isBlank", CharSequence.class);
        context.registerFunction("isNotBlank", isNotBlank);  
        context.registerFunction("isBlank", isBlank);  
        
        if(null != customFunctions){
            for(Method m :customFunctions){
                context.registerFunction(m.getName(), m);
            }
        }
    };
    
    @Override
    @SuppressWarnings("unchecked")
    public List<SpELCompileResult> compile(String spel,String[] contextKeys,Object[] contextValues) throws Exception{
        
        List<SpELCompileResult> compileResult = new ArrayList<SpELCompileResult>();
        
        /**
         * parse the spel expression and set the variables.
         */
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i=0;i<contextValues.length;i++) {
            context.setVariable(contextKeys[i], contextValues[i]);
        }
        
        /**
         * Register custom method.
         */
        this.registerMethod(context);

        /**
         * Compile the SpEL expression.
         */
        Expression parsedExpression = parser.parseExpression(spel,parserContext);
        List<Object> checkResultList = parsedExpression.getValue(context,ArrayList.class);
        String[] expressionArray =  parsedExpression.getExpressionString().split(",");
        
        for(int i=0;i<checkResultList.size();i++){
            SpELCompileResult cr = new SpELCompileResult();
            cr.setExpression(expressionArray[i]);
            cr.setResult(checkResultList.get(i));
            compileResult.add(cr);
        }
        
        return compileResult;
    }
}
