/***
 * @pName proback
 * @name FinanceLogAspect
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.config;

import com.wanhao.proback.annotaion.AspectLog;
import com.wanhao.proback.utils.GsonUtils;
import com.wanhao.proback.utils.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 财务模块日志切面配置类
 */
@Aspect
public class FinanceLogAspectConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private long startTimeMillis = 0;
    private long endTimeMillis = 0;
    private HttpServletRequest request = null;

    @Pointcut("execution(* com.wanhao.proback.service.impl.finance.*.*(..))")
    public void point(){}

    @Before("point()")
    public void before(){
        request = getHttpServletRequest();
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间
    }

    @After("point()")
    public void after(JoinPoint joinPoint){
        request = getHttpServletRequest();
        JsonView jsonView = getJsonView(joinPoint);
        jsonView.setType("after");

        endTimeMillis = System.currentTimeMillis();
        jsonView.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis));
        jsonView.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis));

        String body = GsonUtils.toJsonText(jsonView);

        logger.info(body);
    }

    @AfterReturning(pointcut = "point()",returning = "rvt")
    public void afterReturning(JoinPoint joinPoint, Object rvt){
        JsonView jsonView = getJsonView(joinPoint);
        jsonView.setType("afterReturning");
        jsonView.setRet(rvt);
        if(rvt == null) jsonView.setRet("void");

        String body = GsonUtils.toJsonText(jsonView);
        logger.info(body);
    }

    @AfterThrowing(pointcut = "point()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        JsonView jsonView = getJsonView(joinPoint);
        jsonView.setType("afterThrowing");
        jsonView.setThrowable(ex);
        if(ex == null) jsonView.setThrowable(new Throwable());

        String body = GsonUtils.toJsonText(jsonView);
        logger.info(body);
    }

    /**
     * 获取request对象
     * @return
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 获取方法参数
     * @param joinPoint
     * @return
     */
    private JsonView getJsonView(JoinPoint joinPoint) {
        String url = RequestUtil.getParameters(request);
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取方法描述
        Method[] methods = targetClass.getMethods();
        String desc = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs!=null&&clazzs.length == arguments.length&&method.getAnnotation(AspectLog.class)!=null) {
                    desc = method.getAnnotation(AspectLog.class).description();
                    break;
                }
            }
        }
        JsonView view = new JsonView();
        view.setTargetName(targetName);
        view.setMethodName(methodName);
        view.setArguments(arguments);
        view.setDescription(desc);
        return view;
    }

    class JsonView{
        private String type;
        private String url;
        private String targetName;
        private String methodName;
        private Object[] arguments;
        private String description;
        private String startTime;
        private String endTime;
        private Object ret;
        private Throwable throwable;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTargetName() {
            return targetName;
        }

        public void setTargetName(String targetName) {
            this.targetName = targetName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Object[] getArguments() {
            return arguments;
        }

        public void setArguments(Object[] arguments) {
            this.arguments = arguments;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getRet() {
            return ret;
        }

        public void setRet(Object ret) {
            this.ret = ret;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        public JsonView() {

        }

        public JsonView(String type, String url, String targetName, String methodName, Object[] arguments, String description, String startTime, String endTime, Object ret, Throwable throwable) {

            this.type = type;
            this.url = url;
            this.targetName = targetName;
            this.methodName = methodName;
            this.arguments = arguments;
            this.description = description;
            this.startTime = startTime;
            this.endTime = endTime;
            this.ret = ret;
            this.throwable = throwable;
        }
    }
}
