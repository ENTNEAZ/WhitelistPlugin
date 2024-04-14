package top.nacldragon.whitelistplugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import top.nacldragon.whitelistplugin.WhitelistUtils.Whitelist;

public class PlayerInteractListener implements Listener {

    private static PlayerInteractListener instance;
    public static PlayerInteractListener getInstance() {
        if (instance == null) {
            instance = new PlayerInteractListener();
        }
        return instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getPlayer().isOp()) {
            // OP不用限制
            return;
        }

        if(Whitelist.getInstance().CheckIfUUIDInWhitelist(event.getPlayer().getUniqueId().toString())) {
            //白名单中 不发消息
            return;
        } else {
            //不在白名单中
            event.getPlayer().sendMessage("§b===============================================");
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §a欢迎加入服务器 请使用 /reg <QQ> 注册账号");
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §aJoin our Discord: https://discord.gg/BdQxJZTbqU to get you registered");
            event.getPlayer().sendMessage("§b===============================================");
            event.getPlayer().setGameMode(org.bukkit.GameMode.ADVENTURE);
            event.setCancelled(true);
        }
    }
}
