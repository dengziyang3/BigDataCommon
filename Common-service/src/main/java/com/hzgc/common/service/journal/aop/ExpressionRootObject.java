package com.hzgc.common.service.journal.aop;

/**
 * @author liuzhikun
 * @date 2018/06/08
 */
public class ExpressionRootObject {

    private final Object object;

    private final Object[] args;

    public ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }
}