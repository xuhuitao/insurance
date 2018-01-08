package net.rokyinfo.insurance.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:59:27
 */
@Data
public class R<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;
    private static final long serialVersionUID = 1L;
    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getLocalizedMessage();
        this.code = FAIL;
    }

}
