package com.springbootmyblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect//引入AOP的接口
@Component
public class LogAspect {

    //获取日志记录
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    //声明切面
    //拦截web包下的所有类的所有方法
    @Pointcut("execution(* com.springbootmyblog.controller.*.*(..))")
    public void log(){}

    //在切面之前执行,获取请求
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取request
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //获取url
        String url=request.getRequestURL().toString();
        //获取ip
        String ip=request.getRemoteAddr();
        //获取被拦截的类名.方法名
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
    }
//    //在切面之后执行
//    @After("log()")
//    public void doAfter(){
//        logger.info("-----------doAfter---------");
//
//    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void duAfterReturning(Object result){
        logger.info("Result:{}"+result);
    }


    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
