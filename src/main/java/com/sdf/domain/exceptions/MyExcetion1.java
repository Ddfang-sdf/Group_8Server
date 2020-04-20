package com.sdf.domain.exceptions;

/**
 * 自定义异常1，用于调试DruidUtils
 */
public class MyExcetion1 extends Throwable {
    public MyExcetion1() {
        super();
    }

    public MyExcetion1(String message) {
        super(message);
    }

    public MyExcetion1(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "AccessSqlFalseException [getMessage()=" + getMessage() + ", toString()=" + super.toString() + "]";

    }
}
