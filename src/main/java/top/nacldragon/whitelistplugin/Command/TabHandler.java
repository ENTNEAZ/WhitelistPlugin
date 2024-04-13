package top.nacldragon.whitelistplugin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

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
        if (command.getName().equalsIgnoreCase("register") || command.getName().equalsIgnoreCase("reg")) {
            if (sender.hasPermission("whitelistplugin.register")) {
                if (args.length == 1) {
                    return Arrays.asList("QQ ID");
                }
            }
        }
        return null;
    }
}
