package top.nacldragon.whitelistplugin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.nacldragon.whitelistplugin.QQGroupUtils.QQGroup;
import top.nacldragon.whitelistplugin.WhitelistUtils.Whitelist;

import java.util.Map;

public class CommandHandler implements CommandExecutor {
    private static CommandHandler instance;
    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("reg") || command.getName().equalsIgnoreCase("register")) { // 判断输入的指令是否是 /demo
            if (!(sender instanceof Player)) {
                sender.sendMessage("[WhitelistPlugin]你必须是一名玩家!");
                return true;
            }
            if(((Player) sender).isOp()){
                // OP特有命令
                if (args[0].equalsIgnoreCase("add")) {
                    if (args.length == 3) {
                        Whitelist.getInstance().AddUUIDToWhitelist(args[1],args[2]);
                        sender.sendMessage("§b[WhitelistPlugin]§r §a添加成功");
                        sender.sendMessage("§b[WhitelistPlugin]§r §a请记得将此玩家设置为生存模式");
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        Whitelist.getInstance().RemoveUUIDFromWhitelist(args[1]);
                        sender.sendMessage("§b[WhitelistPlugin]§r §a移除成功");
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    sender.sendMessage("§b[WhitelistPlugin]§r §a白名单列表:");

                    for (Map.Entry<String, String> entry : Whitelist.getInstance().getWhitelistMap().entrySet()) {
                        sender.sendMessage("§b[WhitelistPlugin]§r §a" + entry.getKey() + ":" + entry.getValue());
                    }
                    return true;
                }
            }


            // 判断是否有参数
            if (args.length == 0) {
                sender.sendMessage("§b[WhitelistPlugin]§r §4请输入QQ号码");
                return true;
            }
            if (args.length > 1) {
                sender.sendMessage("§b[WhitelistPlugin]§r §4参数过多");
                return true;
            }

            Player player = (Player) sender;
            //判断重复注册
            if(Whitelist.getInstance().CheckIfUUIDInWhitelist(player.getUniqueId().toString())) {
                player.sendMessage("§b[WhitelistPlugin]§r §a你已经登记过了,无需重复注册");
                return true;
            }

            //使用他人QQ注册
            if(Whitelist.getInstance().CheckIfQQInWhitelist(args[0])) {
                player.sendMessage("§b[WhitelistPlugin]§r §4此QQ已经被登记过了");
                return true;
            }

            // 判断是否在群里
            if (!QQGroup.getInstance().CheckIfUserInGroup(args[0])) {
                player.sendMessage("§b[WhitelistPlugin]§r §4未在群内搜索到此QQ号码，请先加QQ群:" + QQGroup.getInstance().getGroup());
                return true;
            }
            Whitelist.getInstance().AddUUIDToWhitelist(player.getUniqueId().toString(),args[0]);
            player.sendMessage("§b[WhitelistPlugin]§r §a登记成功");
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            return true;

        }
        return false;
    }

}
