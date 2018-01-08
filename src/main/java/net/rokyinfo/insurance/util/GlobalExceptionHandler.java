package net.rokyinfo.insurance.util;

import net.rokyinfo.insurance.entity.ErrorInfo;
import net.rokyinfo.insurance.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(RkInvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo errorHandler(HttpServletRequest req, RkInvalidRequestException e) {
        return ErrorInfo.build(R.error(e.getCode(), e.getMsg(), req.getRequestURI()));
    }

    @ResponseBody
    @ExceptionHandler(RkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo errorHandler(HttpServletRequest req, RkNotFoundException e) {
        return ErrorInfo.build(R.error(e.getCode(), e.getMsg(), req.getRequestURI()));
    }

    @ExceptionHandler(value = RkAuthorizationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorInfo errorHandler(HttpServletRequest req, RkAuthorizationException e) {
        return ErrorInfo.build(R.error(e.getCode(), e.getMsg(), req.getRequestURI()));
    }

    @ExceptionHandler(value = RkForbiddenException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorInfo errorHandler(HttpServletRequest req, RkForbiddenException e) {
        return ErrorInfo.build(R.error(e.getCode(), e.getMsg(), req.getRequestURI()));
    }

    @ExceptionHandler(value = RKException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo errorHandler(HttpServletRequest req, RKException e) {
        return ErrorInfo.build(R.error(e.getCode(), e.getMsg(), req.getRequestURI()));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo errorHandler(HttpServletRequest req, NoHandlerFoundException e) {
        return ErrorInfo.build(R.error(HttpStatus.NOT_FOUND.value(), e.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo errorHandler(HttpServletRequest req, BindException e) {
        return ErrorInfo.build(R.error(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldError().getDefaultMessage(), req.getRequestURI()));
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo errorHandler(HttpServletRequest req, Exception e) {
        logger.error(e.getMessage(), e);
        return ErrorInfo.build(R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), req.getRequestURI()));
    }

}