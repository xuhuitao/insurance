package net.rokyinfo.insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 保险产品表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //版本号
    private Integer version;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //修改者
    private String modifier;
    //创建者
    private String creator;
    //最后修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    //备注
    private String remark;
    //状态 ENABLE 可用 DISABLE 不可用
    private String status;
    //产品编号
    private String code;
    //产品名称
    private String name;
    //产品描述
    private String desc;
    //产品图片
    private String image;
    //单价
    private BigDecimal price;
    //模式：SELF ：自营 OUT:外部链接
    private String model;
    //保险产品外部链接
    private String outUrl;
    //所属保险公司ID
    private Long belong;

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
     * 获取：最后修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置：最后修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：状态 ENABLE 可用 DISABLE 不可用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置：状态 ENABLE 可用 DISABLE 不可用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：产品编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：产品编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：产品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：产品描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置：产品描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取：产品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置：产品图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取：单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：模式：SELF ：自营 OUT:外部链接
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置：模式：SELF ：自营 OUT:外部链接
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取：保险产品外部链接
     */
    public String getOutUrl() {
        return outUrl;
    }

    /**
     * 设置：保险产品外部链接
     */
    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
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
}
