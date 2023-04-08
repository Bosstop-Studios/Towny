package tech.bosstop.country.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import tech.bosstop.country.Country;
import tech.bosstop.country.commands.core.cmd_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CTCommandHandler implements CommandExecutor, TabCompleter {
    private final Country instance = Country.getInstance();
    private HashMap<String, CTCommand> ctCommands = new HashMap<>();

    public CTCommandHandler() {
        instance.getCommand("country").setExecutor(this::onCommand);
        instance.getCommand("country").setTabCompleter(this::onTabComplete);
    }

    public void registerCommands() {
        // Core commands
        new cmd_stats("stats");
    }

    private String[] removeFirst(String[] args) {
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return newArgs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) return true;
        if(ctCommands.containsKey(args[0])) {
            if (ctCommands.get(args[0]).getPermission() != null) {
                if (!commandSender.hasPermission(ctCommands.get(args[0]).getPermission())) {
                    instance.getChat().send(commandSender, "&4You don't have permission to use this command.");
                    return true;
                }
            }
            return ctCommands.get(args[0]).onCommand(commandSender, command, s, removeFirst(args));
        } else {
            instance.getChat().send(commandSender, "&4Unknown command. Type &6/ct help &4for help.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabList = new ArrayList<>();
        if(args.length == 1) {
            tabList.addAll(ctCommands.keySet());
        } else {
            if(ctCommands.containsKey(args[0])) {
                tabList = ctCommands.get(args[0]).onTab(commandSender, command, s, removeFirst(args));
            }
        }
        if(tabList.size() == 0) return null;
        return tabList;
    }

    public void putCommand(String command, CTCommand ctCommand) {
        this.ctCommands.put(command, ctCommand);
    }

    public HashMap<String, CTCommand> getCtCommands() {
        return ctCommands;
    }
}
