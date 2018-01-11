package net.rokyinfo.insurance.enums;

public enum CreatorEnum {

    SYSTEM("system");

    private String creator;

    CreatorEnum(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

}
