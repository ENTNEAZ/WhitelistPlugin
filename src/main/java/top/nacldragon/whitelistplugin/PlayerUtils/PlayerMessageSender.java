package top.nacldragon.whitelistplugin.PlayerUtils;

import org.bukkit.entity.Player;

public class PlayerMessageSender {
    private static PlayerMessageSender instance;
    public static PlayerMessageSender getInstance() {
        if (instance == null) {
            instance = new PlayerMessageSender();
        }
        return instance;
    }

    public void SendPlayerUnwhitelistedMessage(Player player) {
        player.sendMessage("§b===============================================");
        player.sendMessage("§b[WhitelistPlugin]§r §a欢迎加入服务器 请使用 /reg <QQ> 注册账号");
        player.sendMessage("§b[WhitelistPlugin]§r §aJoin our Discord: https://discord.gg/BdQxJZTbqU to get you registered");
        player.sendMessage("§b===============================================");
        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
    }
}
