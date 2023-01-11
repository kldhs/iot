package com.iot.common.util;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.*;

public class JsonUtil {

    //ObjectMapper是线程安全的，使用单例模式
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //添加对java8新time序列化和反序列的支持
    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        objectMapper.registerModule(timeModule);
        //禁止浮点型自动转整型
        objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
    }

    private JsonUtil() {
    }

    public static String toString(Object object) {
        String str;
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + object + " cannot be transformed to a String", e);
        }
        return str;
    }

    public static byte[] toBytes(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + object + " cannot be transformed to a byte array", e);
        }
    }

    public static <T> T toObject(String json, Class<T> cls) {
        T t = null;
        try {
            t = objectMapper.readValue(json, cls);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given Json String value: "
                    + json + " cannot be transformed to a class", e);
        }
        return t;
    }

    public static <T> T toObject(byte[] buf, Class<T> cls) {
        try {
            return objectMapper.readValue(buf, cls);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given Json byte array value: "
                    + Arrays.toString(buf) + " cannot be transformed to a class", e);
        }
    }


    public static Map<String, Object> toMap(String json) {
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given Json String value: "
                    + json + "cannot be transformed to a map", e);
        }
        return map;
    }

    public static <T> Map<String, T> toMap(String json, Class<T> cls) {
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, cls);
        Map<String, T> map = null;
        try {
            map = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given Json String value: "
                    + json + "cannot be transformed to a Map", e);
        }
        return map;
    }

    public static <T> List<T> toList(String json, Class<T> cls) {
        List<T> list = null;
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
        try {
            list = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given Json String value: "
                    + json + "cannot be transformed to a List", e);
        }
        return list;
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json String value: "
                    + json + "cannot be transformed to a JsonNode", e);
        }
    }

    public static JsonNode objectToJsonNode(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static <T> T treeToObject(JsonNode jsonNode, Class<T> cls) {
        try {
            return objectMapper.treeToValue(jsonNode, cls);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given jsonNode: "
                    + jsonNode + "cannot be transformed to a Object", e);
        }
    }

    public static <T> List<T> treeToList(JsonNode jsonNode, Class<T> cls) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
            return objectMapper.treeToValue(jsonNode, javaType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given jsonNode: "
                    + jsonNode + "cannot be transformed to a List", e);
        }
    }

    public static <T> Map<String, T> treeToMap(JsonNode jsonNode, Class<T> cls) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, cls);
            return objectMapper.treeToValue(jsonNode, javaType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given jsonNode: "
                    + jsonNode + "cannot be transformed to a Map", e);
        }
    }

    public static JsonNode convertJsonNode(Object obj) {
        return objectMapper.convertValue(obj, JsonNode.class);
    }
}
