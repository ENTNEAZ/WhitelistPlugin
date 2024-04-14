package top.nacldragon.whitelistplugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.nacldragon.whitelistplugin.PlayerUtils.PlayerMessageSender;
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
            // OP不用限制
            event.getPlayer().sendMessage("§b[WhitelistPlugin]§r §a你是b管理，没人敢惹你");
            return;
        }

        if(Whitelist.getInstance().CheckIfUUIDInWhitelist(event.getPlayer().getUniqueId().toString())) {
            // 在白名单中 不发消息
            return;
        } else {
            PlayerMessageSender.getInstance().SendPlayerUnwhitelistedMessage(event.getPlayer());
        }
    }
}
