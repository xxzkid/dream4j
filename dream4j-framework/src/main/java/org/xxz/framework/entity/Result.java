package org.xxz.framework.entity;

/**
 * json 返回结果
 * @author xxz
 */
public class Result implements java.io.Serializable {

    private static final long serialVersionUID = 353741735599366017L;
    
    private int code;
    private String message;
    private Object object;

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public Result setObject(Object object) {
        this.object = object;
        return this;
    }

}
