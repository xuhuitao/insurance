package net.rokyinfo.insurance.enums;

public enum OrderDispose {

    PASS(1, "审核通过"),
    REFUSE(0, "审核拒绝");

    private int code;

    private String desc;

    OrderDispose(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getOrderDisposeValue() {
        return this.code;
    }

}
