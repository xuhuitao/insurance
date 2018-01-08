package net.rokyinfo.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yuanzhijian on 2017/5/12.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RkNotFoundException extends RKException {

    public RkNotFoundException(String msg){
        super(msg);

    }

    public RkNotFoundException(String msg,int code){
        super(msg,code);

    }
}
