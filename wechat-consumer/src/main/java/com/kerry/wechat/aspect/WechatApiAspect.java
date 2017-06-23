package com.kerry.wechat.aspect;

import com.kerry.model.ClientUser;
import com.kerry.wechat.client.SercretClient;
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
 * Created by wangshen on 2017/6/20.
 */
@Aspect
@Configuration
public class WechatApiAspect {

    private static final Logger logger = LoggerFactory.getLogger(WechatApiAspect.class);

    @Autowired
    private SercretClient sercretClient;

    /**
     * 声明切入点，只处理controller
     */
    @Pointcut("execution(* com.kerry.wechat.controller.*.*(..))")
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
        logger.info(" --- request start params --> url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        /**
         * 校验登录信息
         */
        HttpServletResponse response = sra.getResponse();
        String authorization = request.getHeader("Authorization");
        if(authorization==null||authorization.equals("")){
            logger.info(" --- 未进行登录授权");
            response.setCharacterEncoding("utf-8");
            response.setStatus(-1);//-1去登录
        }
        ClientUser clientUser = sercretClient.getClientUser(authorization);
        if(clientUser==null){
            logger.info(" --- 登录授权信息已过期");
            response.setCharacterEncoding("utf-8");
            response.setStatus(-1);//-1去登录
        }
        String code = clientUser.getAuthCode();
        request.setAttribute("code", code);//设置数据标识码
        request.setAttribute("userId",clientUser.getCode());
        Object o = pjp.proceed();
        return o;
    }
}
