package ml.bosstop.towny.commands.core;

import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class cmd_stats extends TYCommand {

    public cmd_stats(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty stats";
    }

    @Override
    public String getPermission() {
        return "towny.basic";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String[] response = {
                "&bPlugin:&r" + " %plugin%",
                "&bTeams:&r" + " %teams%",
                "&bPlayers:&r" + " %players%",
                "&bServer:&r" + " %server% %server_plugins%",
        };

        for (String line : response) {
            line = line.replace("%plugin%", "&a" + instance.getDescription().getName() + " v" + instance.getDescription().getVersion());
            line = line.replace("%teams%", "&e" + instance.getTeamManager().getTeams().size() + " teams loaded");
            line = line.replace("%players%", "&e" + instance.getPlayerManager().getPlayers().size() + " players loaded");
            line = line.replace("%server%", "&e" + instance.getServerUtils().getServerInfo());
            line = line.replace("%server_plugins%", "&e" + instance.getServerUtils().getServerPlugins());

            instance.getChat().send(sender, line);
        }


        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
