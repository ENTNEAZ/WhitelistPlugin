package top.nacldragon.whitelistplugin.WhitelistUtils;

public class Whitelist {
    private static Whitelist instance;
    private WhitelistStruct[] whitelist;
    public static Whitelist getInstance() {
        if (instance == null) {
            instance = new Whitelist();
        }
        return instance;
    }

    private Whitelist() {
        whitelist = new WhitelistStruct[0];
        // TODO 从文件中读取白名单
    }
    public boolean CheckIfQQInWhitelist(String qq) {
        for (WhitelistStruct whitelistStruct : whitelist) {
            if (whitelistStruct.getQQ().equals(qq)) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckIfUUIDInWhitelist(String uuid) {
        for (WhitelistStruct whitelistStruct : whitelist) {
            if (whitelistStruct.getUUID().equals(uuid)) {
                return true;
            }
        }
        return false;
    }


    public void AddUUIDToWhitelist(String uuid,String qq){
        WhitelistStruct[] oldWhitelist = whitelist;
        whitelist = new WhitelistStruct[whitelist.length + 1];
        System.arraycopy(oldWhitelist, 0, whitelist, 0, oldWhitelist.length);
        whitelist[whitelist.length - 1] = new WhitelistStruct(uuid, qq);
    }
}
