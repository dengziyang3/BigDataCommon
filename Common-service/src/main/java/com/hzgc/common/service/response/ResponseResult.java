package com.hzgc.common.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hzgc.common.service.error.RestErrorTranslator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Restful接口统一响应消息格式
 *
 * @param <T>
 * @author liuzk
 */
@SuppressWarnings("rawtypes")
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String TOTAL = "totalNum";
    private static final String SIZE = "rowNum";
    private static final String DEFAULT_MESSAGE = "OK";
    static final int OK = 0;

    @JsonProperty("resthead")
    private Head head;

    @JsonProperty("restbody")
    private T body;

    @JsonProperty("extend")
    private Map<String, Object> extend;

    private ResponseResult() {
        head = new Head();
    }

    /**
     * 生成默认成功的rest响应消息
     *
     * @param <T> body类型
     * @return T
     */
    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>();
    }

    /**
     * 生成默认成功的rest响应消息
     *
     * @param body 数据
     * @param <T>  body类型
     * @return T
     */
    public static <T> ResponseResult<T> init(T body) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setBody(body);
        return responseResult;
    }

    public static <T> ResponseResult<T> init(T list, long total) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setBody(list);
        responseResult.setTotal(total);
        return responseResult;
    }

    /**
     * 生成错误响应
     *
     * @param errorCode 错误码
     * @param message   错误信息
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(int errorCode, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setError(errorCode, message);
        return result;
    }

    /**
     * 生成响应数据，错误信息从 properties 中读取
     *
     * @param errorCode 错误码
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(int errorCode) {
        return errorEx(errorCode);
    }

    /**
     * 生成响应数据，错误信息从 properties 中读取
     *
     * @param errorCode 错误码
     * @param extra     附加信息
     * @return ResponseResult
     */
    private static <T> ResponseResult<T> errorEx(int errorCode, String... extra) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setErrorEx(errorCode, extra);
        return result;
    }

    public Head getHead() {
        return head;
    }

    public T getBody() {
        return body;
    }

    /**
     * 设置body
     *
     * @param body rest body
     */
    public void setBody(T body) {
        this.body = body;

        if (body instanceof List) {
            int size = ((List) body).size();
            setListSize(size);
            Long total = (Long) extend.get(TOTAL);
            if (total == null || total < size) {
                setTotal(size);
            }
        }
    }

    /**
     * 设置错误信息(从message模板中读取描述信息)
     *
     * @param errorCode 错误码
     * @param args      模板中占位的参数列表
     */
    private void setErrorEx(int errorCode, String... args) {
        String message = RestErrorTranslator.get(errorCode, args);
        setError(errorCode, message);
    }

    /**
     * 设置错误信息
     *
     * @param errorCode 错误码
     * @param message   错误消息
     */
    private void setError(int errorCode, String message) {
        head.setErrorCode(errorCode);
        head.setMessage(message);
    }

    /**
     * 设置总数(分页时使用)
     *
     * @param total 总数
     */
    private void setTotal(long total) {
        if (extend == null) {
            extend = new HashMap<>();
        }
        extend.put(TOTAL, total);
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    /**
     * 增加扩展项
     *
     * @param extend 扩展项
     */
    public void addExtend(Map<String, Object> extend) {
        if (this.extend == null) {
            this.extend = extend;
        } else if (extend != null) {
            this.extend.putAll(extend);
        }
    }

    /**
     * 增加一个扩展项
     *
     * @param key   扩展项key
     * @param value 扩展项value
     */
    public void addExtend(String key, Object value) {
        if (extend == null) {
            extend = new HashMap<>();
        }

        extend.put(key, value);
    }

    private void setListSize(int size) {
        if (extend == null) {
            extend = new HashMap<>();
        }
        extend.put(SIZE, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResponseResult<?> that = (ResponseResult<?>) o;

        return new EqualsBuilder()
                .append(head, that.head)
                .append(body, that.body)
                .append(extend, that.extend)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(head)
                .append(body)
                .append(extend)
                .toHashCode();
    }

    public static class Head {
        private int errorCode = OK;
        private String message = DEFAULT_MESSAGE;

        Head() {
        }

        public Head(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Head head = (Head) o;

            return new EqualsBuilder().
                    append(errorCode, head.errorCode).
                    append(message, head.message).
                    isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).
                    append(errorCode).
                    append(message).
                    toHashCode();
        }
    }

}