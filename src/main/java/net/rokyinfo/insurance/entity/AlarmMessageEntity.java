package net.rokyinfo.insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
/**
 * 告警消息表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-10 23:00:14
 */
public class AlarmMessageEntity implements Serializable {
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
	//设备SN
	private String ccuSn;
	//告警类型  故障告警:0 震动告警:1 内置电池低告警:2 断电告警:4
	private Integer alarmType;
	//消息内容
	private String content;
	//告警时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date alarmTime;
	//所属保险公司ID
	private Long belong;

	/**
	 * 保险服务新增字段
	 */
	//投保人姓名
	private String applicant;
	//手机号码
	private String phoneNumber;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改者
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：修改者
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置：设备SN
	 */
	public void setCcuSn(String ccuSn) {
		this.ccuSn = ccuSn;
	}
	/**
	 * 获取：设备SN
	 */
	public String getCcuSn() {
		return ccuSn;
	}
	/**
	 * 设置：告警类型  0：震动告警 1：断电告警
	 */
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	/**
	 * 获取：告警类型  0：震动告警 1：断电告警
	 */
	public Integer getAlarmType() {
		return alarmType;
	}
	/**
	 * 设置：消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：消息内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：告警时间
	 */
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	/**
	 * 获取：告警时间
	 */
	public Date getAlarmTime() {
		return alarmTime;
	}
	/**
	 * 设置：所属保险公司ID
	 */
	public void setBelong(Long belong) {
		this.belong = belong;
	}
	/**
	 * 获取：所属保险公司ID
	 */
	public Long getBelong() {
		return belong;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
