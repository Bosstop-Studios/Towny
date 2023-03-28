package ml.bosstop.towny.commands.player;

import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_acceptinvite extends TYCommand {

    public cmd_acceptinvite(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty acceptinvite";
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

        instance.getPlayerManager().getPlayer(p.getUniqueId()).setTeam(team);
        instance.getChat().send(sender, "&aYou have joined " + team + "!");

        instance.getTeamManager().removeTeamInvite(p.getUniqueId());

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
