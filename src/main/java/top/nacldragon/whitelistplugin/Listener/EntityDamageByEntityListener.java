package top.nacldragon.whitelistplugin.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import top.nacldragon.whitelistplugin.PlayerUtils.PlayerMessageSender;
import top.nacldragon.whitelistplugin.WhitelistUtils.Whitelist;

public class EntityDamageByEntityListener implements Listener {
    private static EntityDamageByEntityListener instance;

    public static EntityDamageByEntityListener getInstance() {
        if (instance == null) {
            instance = new EntityDamageByEntityListener();
        }
        return instance;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            if (!player.isOp() && !Whitelist.getInstance().CheckIfUUIDInWhitelist(player.getUniqueId().toString())) {
                // 非OP 且 不在白名单中
                PlayerMessageSender.getInstance().SendPlayerUnwhitelistedMessage(player);
                e.setCancelled(true);
            }
        }
    }
}
