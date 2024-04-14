package top.nacldragon.whitelistplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.nacldragon.whitelistplugin.Command.CommandHandler;
import top.nacldragon.whitelistplugin.Command.TabHandler;
import top.nacldragon.whitelistplugin.Listener.PlayerInteractListener;
import top.nacldragon.whitelistplugin.Listener.PlayerJoinListener;
import top.nacldragon.whitelistplugin.QQGroupUtils.QQGroup;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //初始化配置文件 读取配置文件
        this.saveDefaultConfig();
        QQGroup.getInstance().setGroup(this.getConfig().getString("GroupQQID"));
        String miraiAPI = this.getConfig().getString("MiraiAddr");
        if (!miraiAPI.substring(miraiAPI.length() - 1).equals("/")) {
            miraiAPI += "/";
        }
        QQGroup.getInstance().setMiraiAPI(miraiAPI);

        //注册命令
        getCommand("register").setExecutor(CommandHandler.getInstance());
        getCommand("reg").setExecutor(CommandHandler.getInstance());
        getCommand("register").setTabCompleter(TabHandler.getInstance());
        getCommand("reg").setTabCompleter(TabHandler.getInstance());

        //注册监听器
        Bukkit.getServer().getPluginManager().registerEvents(PlayerJoinListener.getInstance(), this);
        Bukkit.getServer().getPluginManager().registerEvents(PlayerInteractListener.getInstance(), this);

        this.getLogger().info("Load Complete");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
