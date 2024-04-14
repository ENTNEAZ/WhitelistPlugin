package top.nacldragon.whitelistplugin.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import top.nacldragon.whitelistplugin.PlayerUtils.PlayerMessageSender;
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
            PlayerMessageSender.getInstance().SendPlayerUnwhitelistedMessage(event.getPlayer());
            event.setCancelled(true);
        }
    }
}
