package net.rokyinfo.insurance.exception;

/**
 * 自定义异常
 *
 * @author zhijian.yuan
 * @email zhijian.yuan@rokyinfo.com
 * @date 2017年05月10日 下午10:11:27
 */
public class RKException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public RKException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RKException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RKException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RKException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
