package cn.lemon.framework.utils;

import com.google.common.collect.Maps;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.Map;

/**
 * JexlUtil 表达式计算 JexlEngine
 *
 * @author lonyee
 * @date 2016-07-14
 */
public class JexlUtil {
    private final static JexlEngine jexl = new JexlEngine();
    
    /**
     * 执行表达式计算
     * @param jexlExp 表达式 如：{if (value == 'blue'){return 1;} else {return 0;}}
     * @param params 参数列表  params.put("value", "blue");
     * @return
     */
    public static Object evaluate(String jexlExp, Map<String, Object> params) {
        Expression e = jexl.createExpression(jexlExp);

        JexlContext context = new MapContext(params);
        return e.evaluate(context);
    }

    public static void main(String[] args) {
        String exp =
                "{" +
                "    if (value == 'blue')" +
                "    {" +
                "        return 1;" +
                "    }" +
                "    else" +
                "    {" +
                "        return 0;" +
                "    }" +
                "}";

        Map<String, Object> params = Maps.newHashMap();
        params.put("value", "blue");
        
        System.out.println(JexlUtil.evaluate(exp, params));
    }
}
