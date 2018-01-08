package net.rokyinfo.insurance.config;


import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanzhijian
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R errorHandler(HttpServletRequest req, Exception e) {
        R<?> result = new R();
        // 已知异常
        if (e instanceof RkException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(R.FAIL);
        }
        // 数据校验异常
        else if (e instanceof BindException) {
            BindException exception = (BindException) e;
            exception.getFieldErrors();
            if (exception.getFieldErrors().size() > 0) {
                result.setMsg(exception.getFieldErrors().get(0).getDefaultMessage());
            } else {
                result.setMsg(e.getLocalizedMessage());
            }
            result.setCode(R.FAIL);
        }
        // 未知异常
        else {
            logger.error(req.getServletPath() + " error ", e);
            result.setMsg(e.toString());
            result.setCode(R.FAIL);
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }
        return result;
    }

}