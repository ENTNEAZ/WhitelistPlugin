package top.nacldragon.whitelistplugin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.nacldragon.whitelistplugin.QQGroupUtils.QQGroup;

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
                sender.sendMessage("你必须是一名玩家!");
                return true;
            }
            if (args.length == 0) { // 判断是否有参数
                sender.sendMessage("请输入QQ号码");
                return true;
            } else if (args.length > 1) {
                sender.sendMessage("参数过多");
                return true;
            }
            Player player = (Player) sender;
            boolean exists = QQGroup.getInstance().CheckIfUserInGroup(args[0]);
            player.sendMessage(exists? "你在群里" : "你不在群里");
            return true;
        }
        return false;
    }

}
