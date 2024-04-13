package top.nacldragon.whitelistplugin.WhitelistUtils;

public class WhitelistStruct {
    private String username;
    private String qq;
    public String getUsername() {
        return username;
    }
    public String getQQ() {
        return qq;
    }
    WhitelistStruct(String username, String qq) {
        this.username = username;
        this.qq = qq;
    }
}
