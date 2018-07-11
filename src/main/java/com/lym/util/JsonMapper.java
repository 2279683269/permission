package com.lym.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/11 15:55
 * @Description:
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //config
        /**反序列化时,遇到未知属性(那些没有对应的属性来映射的属性,并且没有任何setter或handler来处理这样的属性)
         时是否引起结果失败(通过抛JsonMappingException异常)此功能默认是启用的
         */
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        /**
         * 禁用空对象转换json校验
         */
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //过滤类的属性id
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        //在序列化时，只有那些值为null或被认为为空的值的属性才不会被包含在内
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * obj转换成String
     *
     * @param src
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        } else {
            try {
                return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
            } catch (Exception e) {
                log.warn("parse object to String exception", e);
                return null;
            }
        }
    }

    /**
     * String装换成obj
     *
     * @param src
     * @param tTypeReference
     * @param <T>
     * @return
     */
    public static <T> T String2Obj(String src, TypeReference<T> tTypeReference) {
        if (src == null || tTypeReference == null) {
            return null;
        } else {
            try {

                return (T) (tTypeReference.getType().equals(String.class) ? src :
                        objectMapper.readValue(src, tTypeReference));
            } catch (Exception e) {
                log.warn("parse Strin to Object exception,String:{},TypeReference:{},error:{}", src, tTypeReference, e);
                return null;
            }
        }
    }
}


