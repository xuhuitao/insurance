package net.rokyinfo.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yuanzhijian on 2017/5/19.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RkForbiddenException extends RKException{
    public RkForbiddenException(String msg, int code) {
        super(msg, code);
    }
}
