package top.nacldragon.whitelistplugin.WhitelistUtils;

public class WhitelistStruct {
    private String qq;
    private String uuid;

    public String getQQ() {
        return qq;
    }
    public String getUUID() {
        return uuid;
    }
    WhitelistStruct(String uuid, String qq) {
        this.uuid = uuid;
        this.qq = qq;
    }
}
