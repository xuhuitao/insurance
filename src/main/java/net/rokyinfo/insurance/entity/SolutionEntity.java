package net.rokyinfo.insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.rokyinfo.insurance.util.FormatUtils;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 保险产品方案表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@ExcelTarget("solutionEntity")
public class SolutionEntity implements Serializable {
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
    //保险产品ID
    private Long productId;
    //方案编号
    private String code;
    //方案名称
    private String name;
    //方案描述
    private String desc;
    //方案图片
    private String image;
    //单价
    private BigDecimal price;
    //保险额度
    private BigDecimal coverage;
    //有效期：单位月
    private Integer indate;

    /**
     * excel导出字段
     */
    //保险额度
    @Excel(name = "保额", orderNum = "6")
    private BigDecimal coverageExcel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    public Integer getIndate() {
        return indate;
    }

    public void setIndate(Integer indate) {
        this.indate = indate;
    }

    public String getCoverageExcel() {
        if (coverage == null) {
            return "";
        }
        if (coverage.intValue() == 0) {
            return "";
        }
        return FormatUtils.formatDecimal(coverage, FormatUtils.TWO_DECIMAL);
    }

    public void setCoverageExcel(BigDecimal coverageExcel) {
        this.coverageExcel = coverageExcel;
    }
}
