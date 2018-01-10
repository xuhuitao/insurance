package net.rokyinfo.insurance.entity;

/**
 * @author yuanzhijian
 */
public class AlarmMsg {

    private String ccSn;
    private String alert;
    private String userId;
    private String content;
    private String firmFlag;
    private String alarmType;

    public String getCcSn() {
        return ccSn;
    }

    public void setCcSn(String ccSn) {
        this.ccSn = ccSn;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirmFlag() {
        return firmFlag;
    }

    public void setFirmFlag(String firmFlag) {
        this.firmFlag = firmFlag;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    @Override
    public String toString() {
        return "AlarmMsg{" +
                "ccSn='" + ccSn + '\'' +
                ", alert='" + alert + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", firmFlag='" + firmFlag + '\'' +
                ", alarmType='" + alarmType + '\'' +
                '}';
    }
}
