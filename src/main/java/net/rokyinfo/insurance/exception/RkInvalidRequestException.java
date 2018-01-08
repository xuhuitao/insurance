package net.rokyinfo.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yuanzhijian on 2017/5/17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RkInvalidRequestException extends RkException {

    public RkInvalidRequestException(String msg, int code) {
        super(msg, code);
    }
}
