package ml.bosstop.towny.commands;

import ml.bosstop.towny.Towny;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class TYCommand {

    protected Towny instance;

    public TYCommand(String command) {
        super();
        this.instance = Towny.getInstance();
        instance.getCommandHandler().putCommand(command, this);
    }

    protected boolean argsNeeded(CommandSender sender, String[] args, String usage, int argsNeeded) {
        if (args.length < argsNeeded) {
            instance.getChat().send(sender, "Not enough arguments.");
            instance.getChat().send(sender, "Usage: " + usage);
            return true;
        }
        return false;
    }

    protected List<String> getPermissions() {
        List<String> permissionList = new ArrayList<>();
        Bukkit.getPluginManager().getPermissions().forEach(permission -> permissionList.add(permission.getName()));
        return permissionList;
    }

    protected List<String> getPlayerList() {
        List<String> playerList = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> playerList.add(player.getName()));
        return playerList;
    }

    public abstract String getUsage();

    public abstract String getPermission();

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    public abstract List<String> onTab(CommandSender sender, Command command, String label, String[] args);
}
