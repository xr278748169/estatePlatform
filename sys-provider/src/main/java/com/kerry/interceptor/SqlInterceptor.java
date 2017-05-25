package com.kerry.interceptor;

import org.apache.log4j.Logger;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.InterceptorContext;
import org.beetl.sql.core.engine.SQLParameter;

/**
 * Created by wangshen on 2017/5/16.
 */
public class SqlInterceptor implements Interceptor {

    private final Logger logger = Logger.getLogger(getClass());

    public SqlInterceptor(){
    }

    /**
     * sql执行前操作
     * @param interceptorContext
     */
    @Override
    public void before(InterceptorContext interceptorContext) {
        String sql = interceptorContext.getSql();
        logger.info(" ---> 执行的sql -- "+sql);
    }

    /**
     * sql执行后操作
     * @param interceptorContext
     */
    @Override
    public void after(InterceptorContext interceptorContext) {

    }

    /**
     * 异常处理
     * @param interceptorContext
     * @param e
     */
    @Override
    public void exception(InterceptorContext interceptorContext, Exception e) {

    }
}
