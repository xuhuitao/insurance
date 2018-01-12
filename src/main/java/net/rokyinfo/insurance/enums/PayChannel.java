package net.rokyinfo.insurance.enums;

public enum PayChannel {

    ALI_PAY(2, "支付宝");

    private long id;

    private String desc;

    PayChannel(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public long getPayChannelId() {
        return id;
    }

}
