package net.rokyinfo.insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 保险订单表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //版本号
    private Integer version;
    //创建者
    private String creator;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //修改者
    private String modifier;
    //修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    //订单状态   0:待支付 ；1：已支付,待审核  2： 已生效，保障中 3：已过期 4：已拒绝，未退款 5：已拒绝，已退款
    private Integer status;
    //投保人姓名
    @NotNull(message = "投保人姓名不能为空")
    private String applicant;
    //手机号码
    @NotNull(message = "手机号码不能为空")
    private String phoneNumber;
    //证件类型
    @NotNull(message = "证件类型不能为空")
    private Integer idType;
    //身份证号码
    @NotNull(message = "身份证号码不能为空")
    private String idNumber;
    //省
    @NotNull(message = "省不能为空")
    private String province;
    //市
    @NotNull(message = "市不能为空")
    private String city;
    //区
    private String district;
    //车辆品牌
    @NotNull(message = "车辆品牌不能为空")
    private String brand;
    //车辆型号
    @NotNull(message = "车辆型号不能为空")
    private String model;
    //车架号
    @NotNull(message = "车架号不能为空")
    private String vin;
    //车辆购买日期
    @NotNull(message = "车辆购买日期不能为空")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyTime;
    //车辆购买价格
    @NotNull(message = "车辆购买价格不能为空")
    private BigDecimal buyPrice;
    //发票图片
    private String billImg;
    //车辆图片
    private List<String> scooterImg;
    //中控SN号
    @NotNull(message = "中控SN号不能为空")
    private String ccuSn;
    //所属保险公司ID
    private Long belong;
    //产品
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    private ProductEntity insuranceProductEntity;
    //产品方案
    @NotNull(message = "产品方案不能为空")
    private Long solutionId;
    private SolutionEntity solutionEntity;
    //订单价格
    private BigDecimal price;
    //订单流水号
    private String orderNo;
    //支付流水号
    private String trxNo;
    //支付方式
    private String payment;
    //激活时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activationTime;
    //到期时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTime;
    //骑多多系统用户ID
    @NotNull(message = "用户ID不能为空")
    private String userId;
    //上传的发票图片
    private MultipartFile billFile;
    //上传的车辆图片
    private MultipartFile[] scooterFiles;

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置：版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取：创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置：创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：修改者
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置：修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取：修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置：修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取：订单状态   0:待支付 ；1：已支付,待审核  2： 已生效，保障中 3：已过期 4：已拒绝，未退款 5：已拒绝，已退款
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：订单状态   0:待支付 ；1：已支付,待审核  2： 已生效，保障中 3：已过期 4：已拒绝，未退款 5：已拒绝，已退款
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：投保人姓名
     */
    public String getApplicant() {
        return applicant;
    }

    /**
     * 设置：投保人姓名
     */
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取：证件类型
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * 设置：证件类型
     */
    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    /**
     * 获取：身份证号码
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置：身份证号码
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取：省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置：省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置：市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置：区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：车辆品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置：车辆品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取：车辆型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置：车辆型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取：车架号
     */
    public String getVin() {
        return vin;
    }

    /**
     * 设置：车架号
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * 获取：购买日期
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * 设置：购买日期
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    /**
     * 获取：购买价格
     */
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /**
     * 设置：购买价格
     */
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * 获取：发票图片
     */
    public String getBillImg() {
        return billImg;
    }

    /**
     * 设置：发票图片
     */
    public void setBillImg(String billImg) {
        this.billImg = billImg;
    }

    /**
     * 获取：车辆图片
     */
    public List<String> getScooterImg() {
        return scooterImg;
    }

    /**
     * 设置：车辆图片
     */
    public void setScooterImg(List<String> scooterImg) {
        this.scooterImg = scooterImg;
    }

    /**
     * 获取：中控SN号
     */
    public String getCcuSn() {
        return ccuSn;
    }

    /**
     * 设置：中控SN号
     */
    public void setCcuSn(String ccuSn) {
        this.ccuSn = ccuSn;
    }

    /**
     * 获取：所属保险公司ID
     */
    public Long getBelong() {
        return belong;
    }

    /**
     * 设置：所属保险公司ID
     */
    public void setBelong(Long belong) {
        this.belong = belong;
    }

    public ProductEntity getInsuranceProductEntity() {
        return insuranceProductEntity;
    }

    public void setInsuranceProductEntity(ProductEntity insuranceProductEntity) {
        this.insuranceProductEntity = insuranceProductEntity;
    }

    public SolutionEntity getSolutionEntity() {
        return solutionEntity;
    }

    public void setSolutionEntity(SolutionEntity solutionEntity) {
        this.solutionEntity = solutionEntity;
    }

    /**
     * 获取：订单价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：订单价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：订单流水号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置：订单流水号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取：支付流水号
     */
    public String getTrxNo() {
        return trxNo;
    }

    /**
     * 设置：支付流水号
     */
    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    /**
     * 获取：支付方式
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 设置：支付方式
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * 获取：激活时间
     */
    public Date getActivationTime() {
        return activationTime;
    }

    /**
     * 设置：激活时间
     */
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * 获取：到期时间
     */
    public Date getExpirationTime() {
        return expirationTime;
    }

    /**
     * 设置：到期时间
     */
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MultipartFile getBillFile() {
        return billFile;
    }

    public void setBillFile(MultipartFile billFile) {
        this.billFile = billFile;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public MultipartFile[] getScooterFiles() {
        return scooterFiles;
    }

    public void setScooterFiles(MultipartFile[] scooterFiles) {
        this.scooterFiles = scooterFiles;
    }
}
