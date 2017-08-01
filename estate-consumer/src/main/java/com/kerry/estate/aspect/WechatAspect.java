package com.kerry.estate.aspect;

import com.alibaba.fastjson.JSONObject;
import com.kerry.estate.client.WechatUserCacheClient;
import com.kerry.model.ClientUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信页面校验器
 * Created by wangshen on 2017/7/31.
 */
@Aspect
@Configuration
public class WechatAspect {

    private static final Logger logger = LoggerFactory.getLogger(WechatAspect.class);

    @Autowired
    private WechatUserCacheClient wechatUserCacheClient;

    /**
     * 声明切入点，只处理web
     */
    @Pointcut("execution(* com.kerry.estate.*.web.*.*(..))")
    private void pointCutMethod() {
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore() {
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        logger.info(" --- 响应结果 --> " + result);
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        logger.error(" --- 异常信息 --> "+e.getMessage(),new Throwable(e));
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
    }


    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.debug(" --- request start params --> url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        //根据用户的token换取用户的微信编号
        /**
         * 校验系统授权信息
         */
        HttpServletResponse response = sra.getResponse();
        String authorization = request.getHeader("Authorization");
        if(authorization==null||authorization.equals("")){
            logger.info(" --- 微信未进行授权");
            response.setCharacterEncoding("utf-8");
            response.setStatus(403);
            response.getWriter().write("<body style='font-size:20'>Http Status: 403 未进行授权</body>");
            return null;
        }
        String userCache = wechatUserCacheClient.getUserCache(authorization);
        if(userCache==null || userCache.equals("")){
            logger.info(" --- 微信授权信息已过期");
            response.setCharacterEncoding("utf-8");
            response.setStatus(403);
            response.getWriter().write("<body style='font-size:20'>Http Status: 授权信息已过期</body>");
            return null;
        }
        JSONObject cacheJson = JSONObject.parseObject(userCache);
        String accountId = cacheJson.getString("accountId");
        String tuId = cacheJson.getString("tuId");
        request.setAttribute("accountId", accountId);
        request.setAttribute("tuId", tuId);//微信用户ID
        Object o = pjp.proceed();
        return o;
    }
}
