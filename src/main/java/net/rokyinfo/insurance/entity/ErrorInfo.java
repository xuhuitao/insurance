package net.rokyinfo.insurance.entity;

import net.rokyinfo.insurance.util.R;
/**
 * Created by yuanzhijian on 2017/5/17.
 */
public class ErrorInfo {

    private R error;

    public static ErrorInfo build(R error){
        ErrorInfo mErrorInfo = new ErrorInfo();
        mErrorInfo.setError(error);
        return mErrorInfo;
    }

    public R getError() {
        return error;
    }

    public void setError(R error) {
        this.error = error;
    }
}
