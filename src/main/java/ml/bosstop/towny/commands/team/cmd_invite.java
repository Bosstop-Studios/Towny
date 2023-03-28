package ml.bosstop.towny.commands.team;

import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.commons.structures.core.TYTeam;
import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class cmd_invite extends TYCommand {

    public cmd_invite(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty invite <player>";
    }

    @Override
    public String getPermission() {
        return "towny.basic";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(argsNeeded(sender, args, getUsage(), 1)) return true;

        Player p = Bukkit.getPlayer(sender.getName());
        Player target = Bukkit.getPlayer(args[0]);

        TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(p.getUniqueId());

        if(tyPlayer.getTeam() == null) {
            instance.getChat().send(sender, "&cYou are not in a team.");
            return true;
        }

        if(target == null) {
            instance.getChat().send(sender, "&cThat player is not online.");
            return true;
        }

        TYTeam tyTeam = instance.getTeamManager().getTeam(tyPlayer.getTeam());

        if(tyTeam.getLeader().equals(p.getUniqueId())) {
            instance.getTeamManager().addTeamInvite(target.getUniqueId(), tyTeam.getName());
            instance.getChat().send(sender, "&aYou have invited " + target.getName() + " to your team.");
            instance.getChat().send(target, "&aYou have been invited to join " + tyTeam.getName() + "!");
        } else {
            instance.getChat().send(sender, "&cYou are not the leader of this team and cannot invite players.");
        }
        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            return getPlayerList();
        }
        return null;
    }
}
