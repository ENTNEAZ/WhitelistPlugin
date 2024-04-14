package top.nacldragon.whitelistplugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.nacldragon.whitelistplugin.WhitelistUtils.Whitelist;

public class PlayerJoinListener implements Listener {
    private static PlayerJoinListener instance;
    public static PlayerJoinListener getInstance() {
        if (instance == null) {
            instance = new PlayerJoinListener();
        }
        return instance;
    }
    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        if(event.getPlayer().isOp()) {
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §a你是b管理，没人敢惹你");
            return;
        }

        if(Whitelist.getInstance().CheckIfUUIDInWhitelist(event.getPlayer().getUniqueId().toString())) {
            // 在白名单中 不发消息
            return;
        } else {
            event.getPlayer().sendMessage("§b===============================================");
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §a欢迎加入服务器 请使用 /reg <QQ> 注册账号");
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §aJoin our Discord: https://discord.gg/BdQxJZTbqU to get you registered");
            event.getPlayer().sendMessage("§b===============================================");
            event.getPlayer().setGameMode(org.bukkit.GameMode.ADVENTURE);
        }
    }
}
