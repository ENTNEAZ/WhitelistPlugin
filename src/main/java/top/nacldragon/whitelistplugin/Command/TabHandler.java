package top.nacldragon.whitelistplugin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import top.nacldragon.whitelistplugin.WhitelistUtils.Whitelist;

import java.util.Arrays;
import java.util.List;

public class TabHandler implements TabCompleter {
    private static TabHandler instance;
    public static TabHandler getInstance() {
        if (instance == null) {
            instance = new TabHandler();
        }
        return instance;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        if (command.getName().equalsIgnoreCase("register") || command.getName().equalsIgnoreCase("reg")) {
            if (((Player) sender).isOp()){
                //OP特有
                /*
                /reg add <UUID> <QQ>
                /reg remove <UUID>
                /reg list
                 */
                switch (args.length){
                    case 1:
                        // /reg <add/remove/list>
                        return Arrays.asList("add", "remove", "list");
                    case 2:
                        // /reg add <UUID> <QQ>
                        // /reg remove <UUID>
                        if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("add")) {
                            return Arrays.asList("<UUID>");
                        }
                        break;
                    case 3:
                        // /reg add <UUID> <QQ>
                        if (args[0].equalsIgnoreCase("add")) {
                            return Arrays.asList("<QQ>");
                        }
                        break;

                }
                return null;
            }

            if (sender.hasPermission("whitelistplugin.register") && !Whitelist.getInstance().CheckIfUUIDInWhitelist(((Player) sender).getUniqueId().toString())) {
                //普通人有的
                /*
                /reg <QQ>
                 */
                if (args.length == 1) {
                    return Arrays.asList("<QQ>");
                }
            }
        }
        return null;
    }
}
