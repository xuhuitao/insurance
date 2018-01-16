package net.rokyinfo.insurance.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yuanzhijian
 */
public class PayOrderNotifyMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    //状态(参考枚举:orderstatusenum)
    private String status;
    //商户订单号
    private String merchantOrderNo;
    //订单金额
    private BigDecimal orderAmount;
    //支付流水号
    private String trxNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    @Override
    public String toString() {
        return "PayOrderNotifyMsg{" +
                "status='" + status + '\'' +
                ", merchantOrderNo='" + merchantOrderNo + '\'' +
                ", orderAmount=" + orderAmount +
                ", trxNo='" + trxNo + '\'' +
                '}';
    }
}
