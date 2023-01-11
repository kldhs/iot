package com.iot.common.util.redis;


import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class FunctionUtils {

    private FunctionUtils() {
    }

    /**
     * 获取实现了函数式接口的方法名,字段名
     *
     * @param fn  函数式接口实现
     * @param <T> 方法返回类型
     * @return 字段名称
     */
    public static <T> String getFieldName(SFunction<T, ?> fn) {
        SerializedLambda serializedLambda;
        try {
            Method writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
            writeReplaceMethod.setAccessible(true);
            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(fn);
        } catch (Exception e) {
            throw new IllegalArgumentException("get lambda field name error");
        }
        String methodName = serializedLambda.getImplMethodName();
        String fieldName = methodName.replace("get", "");
        char[] cs = fieldName.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }


}
