package ml.bosstop.towny.commands.player;

import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_declineinvite extends TYCommand {

    public cmd_declineinvite(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty declineinvite";
    }

    @Override
    public String getPermission() {
        return "towny.basic";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = Bukkit.getPlayer(sender.getName());

        assert p != null;
        String team = instance.getTeamManager().getTeamInvite(p.getUniqueId());

        if(team == null) {
            instance.getChat().send(sender, "&cYou have no pending invites.");
            return true;
        }

        instance.getTeamManager().removeTeamInvite(p.getUniqueId());
        instance.getChat().send(sender, "&aYou have declined the invite to " + team + "!");
        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
