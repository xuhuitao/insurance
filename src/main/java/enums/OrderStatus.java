package enums;

public enum OrderStatus {

    TO_PAY(0, "待支付"),
    PAYED_TO_VERIFY(1, "已支付,待审核"),
    IN_INSURANCE(2, "已生效，保障中"),
    EXPIRE(3, "已过期"),
    REFUSE_AND_UNREFUND(4, "已拒绝，未退款"),
    REFUSE_AND_REFUND(5, "已拒绝，未退款");

    private int status;

    private String desc;

    OrderStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getOrderStatusValue() {
        return status;
    }

}
