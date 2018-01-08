package net.rokyinfo.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yuanzhijian on 2017/5/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RkAuthorizationException extends RKException {

    public RkAuthorizationException(String msg, int code) {
        super(msg, code);
    }

}
