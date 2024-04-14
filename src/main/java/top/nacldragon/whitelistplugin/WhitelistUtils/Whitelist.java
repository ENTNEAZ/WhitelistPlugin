package top.nacldragon.whitelistplugin.WhitelistUtils;

import org.bukkit.configuration.file.YamlConfiguration;
import top.nacldragon.whitelistplugin.WhitelistPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Whitelist {
    private static Whitelist instance;
    private Map<String, String> whitelistMap;// UUID -> QQ

    private YamlConfiguration yamlWhitelist;
    private File yamlWhitelistFile;

    public static Whitelist getInstance() {
        if (instance == null) {
            instance = new Whitelist();
        }
        return instance;
    }

    private Whitelist() {
        whitelistMap = new HashMap<>();
    }
    public boolean CheckIfQQInWhitelist(String qq) {
        return whitelistMap.containsValue(qq);
    }

    public boolean CheckIfUUIDInWhitelist(String uuid) {
        return whitelistMap.containsKey(uuid);
    }


    public void AddUUIDToWhitelist(String uuid,String qq){
        whitelistMap.put(uuid,qq);
        SaveToYamlConfiguration();
    }

    public void RemoveUUIDFromWhitelist(String uuid) {
        whitelistMap.remove(uuid);
        SaveToYamlConfiguration();
    }

    public void LoadFromYamlConfiguration(YamlConfiguration y,File f) {
        this.yamlWhitelist = y;
        this.yamlWhitelistFile = f;
        List<String> w = yamlWhitelist.getStringList("whitelist");

        for (int i = 0; i < w.size(); i++) {
            String[] s = w.get(i).split(":");
            whitelistMap.put(s[0],s[1]);
        }

    }

    public void SaveToYamlConfiguration() {
        ArrayList<String> w = new java.util.ArrayList<>();
        for (Map.Entry<String, String> entry : whitelistMap.entrySet()) {
            w.add(entry.getKey() + ":" + entry.getValue());
        }
        yamlWhitelist.set("whitelist", w);

        try {
            yamlWhitelist.save(yamlWhitelistFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getWhitelistMap() {
        return whitelistMap;
    }
}
