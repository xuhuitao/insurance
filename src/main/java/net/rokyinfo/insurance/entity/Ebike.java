package net.rokyinfo.insurance.entity;

public class Ebike {

    private int status;
    private double lon;
    private double lat;
    private int odo;
    private int speed;
    private String fault;
    private String faultTime;
    private int voltage;
    private int current;
    private String ccuSn;
    private String dcuImei;
    private int dcuVbat;
    private int dcuGpsRssi;
    private int dcuGsmRssi;
    private int bmsSoc;
    private int bmsSoh;
    private int source;
    private String reportTime;
    private String createTime;
    private boolean online;
    private boolean faultFlag;
    private String simValidityTime;
    private boolean insuranceFlag;
    private String insuranceId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getOdo() {
        return odo;
    }

    public void setOdo(int odo) {
        this.odo = odo;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getCcuSn() {
        return ccuSn;
    }

    public void setCcuSn(String ccuSn) {
        this.ccuSn = ccuSn;
    }

    public String getDcuImei() {
        return dcuImei;
    }

    public void setDcuImei(String dcuImei) {
        this.dcuImei = dcuImei;
    }

    public int getDcuVbat() {
        return dcuVbat;
    }

    public void setDcuVbat(int dcuVbat) {
        this.dcuVbat = dcuVbat;
    }

    public int getDcuGpsRssi() {
        return dcuGpsRssi;
    }

    public void setDcuGpsRssi(int dcuGpsRssi) {
        this.dcuGpsRssi = dcuGpsRssi;
    }

    public int getDcuGsmRssi() {
        return dcuGsmRssi;
    }

    public void setDcuGsmRssi(int dcuGsmRssi) {
        this.dcuGsmRssi = dcuGsmRssi;
    }

    public int getBmsSoc() {
        return bmsSoc;
    }

    public void setBmsSoc(int bmsSoc) {
        this.bmsSoc = bmsSoc;
    }

    public int getBmsSoh() {
        return bmsSoh;
    }

    public void setBmsSoh(int bmsSoh) {
        this.bmsSoh = bmsSoh;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isFaultFlag() {
        return faultFlag;
    }

    public void setFaultFlag(boolean faultFlag) {
        this.faultFlag = faultFlag;
    }

    public String getSimValidityTime() {
        return simValidityTime;
    }

    public void setSimValidityTime(String simValidityTime) {
        this.simValidityTime = simValidityTime;
    }

    public boolean isInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(boolean insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }
}
