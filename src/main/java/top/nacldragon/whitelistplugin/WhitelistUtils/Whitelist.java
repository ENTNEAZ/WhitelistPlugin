package top.nacldragon.whitelistplugin.WhitelistUtils;

import org.bukkit.configuration.file.YamlConfiguration;
import top.nacldragon.whitelistplugin.WhitelistPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Whitelist {
    private static Whitelist instance;
    private WhitelistStruct[] whitelist;

    private YamlConfiguration yamlWhitelist;
    private File yamlWhitelistFile;

    public static Whitelist getInstance() {
        if (instance == null) {
            instance = new Whitelist();
        }
        return instance;
    }

    private Whitelist() {
        whitelist = new WhitelistStruct[0];
    }
    public boolean CheckIfQQInWhitelist(String qq) {
        // 线性搜索 可以改HashMap
        for (WhitelistStruct whitelistStruct : whitelist) {
            if (whitelistStruct.getQQ().equals(qq)) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckIfUUIDInWhitelist(String uuid) {
        // 线性搜索 可以改HashMap
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
        SaveToYamlConfiguration();
    }

    public void RemoveUUIDFromWhitelist(String arg) {
        WhitelistStruct[] oldWhitelist = whitelist;
        whitelist = new WhitelistStruct[whitelist.length - 1];
        int i = 0;
        for (WhitelistStruct whitelistStruct : oldWhitelist) {
            if (!whitelistStruct.getUUID().equals(arg)) {
                whitelist[i] = whitelistStruct;
                i++;
            }
        }
        SaveToYamlConfiguration();
    }

    public void LoadFromYamlConfiguration(YamlConfiguration y,File f) {
        this.yamlWhitelist = y;
        this.yamlWhitelistFile = f;
        List<String> w = yamlWhitelist.getStringList("whitelist");

        whitelist = new WhitelistStruct[w.size()];
        for (int i = 0; i < w.size(); i++) {
            String[] s = w.get(i).split(":");
            whitelist[i] = new WhitelistStruct(s[0], s[1]);
        }

    }

    public void SaveToYamlConfiguration() {
        List<String> w = new java.util.ArrayList<>();
        for (int i = 0; i < this.whitelist.length; i++) {
            w.add(whitelist[i].getUUID() + ":" + whitelist[i].getQQ());
        }
        yamlWhitelist.set("whitelist", w);

        try {
            yamlWhitelist.save(yamlWhitelistFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WhitelistStruct[] getWhitelist() {
        return whitelist;
    }
}
