package ml.bosstop.towny.commands.team;

import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.commons.structures.core.TYTeam;
import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_createteam extends TYCommand {

    public cmd_createteam(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty createteam <name>";
    }

    @Override
    public String getPermission() {
        return "towny.basic";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(argsNeeded(sender, args, getUsage(), 1)) return true;

        Player p = Bukkit.getPlayer(sender.getName());

        assert p != null;
        TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(p.getUniqueId());

        if(tyPlayer.getTeam() != null) {
            instance.getChat().send(sender, "&cYou are already in a town.");
            return true;
        }

        if(instance.getTeamManager().getTeam(args[0]) != null) {
            instance.getChat().send(sender, "&cA town with that name already exists.");
            return true;
        }

        TYTeam newTeam = new TYTeam(args[0], "&a[&f" + args[0] + "&f]&r", p.getUniqueId());
        instance.getTeamManager().addTeam(newTeam);
        instance.getChat().send(sender, "&aYou have created a town named " + args[0] + "!");

        return false;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
