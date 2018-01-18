package net.rokyinfo.insurance.enums;

/**
 * @author: yangyang.cao
 * @date: 2018-01-18 14:07
 **/
public enum TradeStatusEnum {

    /**
     * 交易成功 交易支付成功
     */
    SUCCESS("SUCCESS", "交易成功"),
    /**
     * 交易关闭  未付款交易超时关闭，或支付完成后全额退款
     */
    CLOSED("CLOSED", "交易关闭");

    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 描述
     */
    private String desc;

    private TradeStatusEnum(String tradeStatus, String desc) {
        this.tradeStatus = tradeStatus;
        this.desc = desc;
    }

    public String getTradeStatus() {
        return this.tradeStatus;
    }

}
