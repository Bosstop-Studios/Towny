package ml.bosstop.towny.commands;

import ml.bosstop.towny.Towny;
import ml.bosstop.towny.commands.team.cmd_createteam;
import ml.bosstop.towny.commands.team.cmd_disbandteam;
import ml.bosstop.towny.commands.team.cmd_invite;
import ml.bosstop.towny.commands.player.cmd_acceptinvite;
import ml.bosstop.towny.commands.player.cmd_chat;
import ml.bosstop.towny.commands.player.cmd_declineinvite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TYCommandHandler implements CommandExecutor, TabCompleter {
    private final Towny instance = Towny.getInstance();
    private HashMap<String, TYCommand> tyCommands = new HashMap<>();

    public TYCommandHandler() {
        instance.getCommand("towny").setExecutor(this::onCommand);
        instance.getCommand("towny").setTabCompleter(this::onTabComplete);
    }

    public void registerCommands() {
        // Player commands
        new cmd_chat("chat");
        new cmd_acceptinvite("acceptinvite");
        new cmd_declineinvite("declineinvite");

        // Team commands
        new cmd_createteam("createteam");
        new cmd_disbandteam("disbandteam");
        new cmd_invite("invite");
    }

    private String[] removeFirst(String[] args) {
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return newArgs;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) return true;
        if(tyCommands.containsKey(args[0])) {
            if (tyCommands.get(args[0]).getPermission() != null) {
                if (!commandSender.hasPermission(tyCommands.get(args[0]).getPermission())) {
                    instance.getChat().send(commandSender, "&4You don't have permission to use this command.");
                    return true;
                }
            }
            return tyCommands.get(args[0]).onCommand(commandSender, command, s, removeFirst(args));
        } else {
            instance.getChat().send(commandSender, "&4Unknown command. Type &6/pr help &4for help.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabList = new ArrayList<>();
        if(args.length == 1) {
            tabList.addAll(tyCommands.keySet());
        } else {
            if(tyCommands.containsKey(args[0])) {
                tabList = tyCommands.get(args[0]).onTab(commandSender, command, s, removeFirst(args));
            }
        }
        if(tabList.size() == 0) return null;
        return tabList;
    }

    public void putCommand(String command, TYCommand tyCommand) {
        this.tyCommands.put(command, tyCommand);
    }

    public HashMap<String, TYCommand> getPrCommands() {
        return tyCommands;
    }
}
