package net.rokyinfo.insurance.enums;

public enum PayType {

    APP("APP", "从APP进行支付"),
    WAP("WAP", "手机网站支付");



    private String type;

    private String desc;

    PayType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getPayType() {
        return type;
    }

}
