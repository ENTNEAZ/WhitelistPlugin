package top.nacldragon.whitelistplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.nacldragon.whitelistplugin.Command.CommandHandler;
import top.nacldragon.whitelistplugin.Command.TabHandler;
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



        getCommand("register").setExecutor(CommandHandler.getInstance());
        getCommand("reg").setExecutor(CommandHandler.getInstance());
        getCommand("register").setTabCompleter(TabHandler.getInstance());
        getCommand("reg").setTabCompleter(TabHandler.getInstance());

        this.getLogger().info("Load Complete");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
