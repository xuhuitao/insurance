package net.rokyinfo.insurance.aop;

import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.util.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yuanzhijian
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);


    @Pointcut("execution(public net.rokyinfo.insurance.util.R *(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        R<?> result;
        try {
            result = (R<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    private R<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        R<?> result = new R();
        // 已知异常
        if (e instanceof RkException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(R.FAIL);
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            result.setMsg(e.toString());
            result.setCode(R.FAIL);
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }
        return result;
    }
}