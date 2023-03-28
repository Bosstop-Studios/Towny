package ml.bosstop.towny.commands.team;

import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.commons.structures.core.TYTeam;
import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_disbandteam extends TYCommand {

    public cmd_disbandteam(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty disbandteam";
    }

    @Override
    public String getPermission() {
        return "towny.basic";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = Bukkit.getPlayer(sender.getName());
        TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(p.getUniqueId());

        if(tyPlayer.getTeam() == null) {
            instance.getChat().send(sender, "&cYou are not in a team.");
            return true;
        }

        TYTeam tyTeam = instance.getTeamManager().getTeam(tyPlayer.getTeam());

        if(tyTeam.getLeader().equals(p.getUniqueId())) {
            instance.getTeamManager().removeTeam(tyTeam);
            instance.getChat().send(sender, "&aYou have disbanded your team.");
        } else {
            instance.getChat().send(sender, "&cYou are not the leader of this team and cannot disband it.");
        }

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
