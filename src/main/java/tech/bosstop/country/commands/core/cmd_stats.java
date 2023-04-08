package tech.bosstop.country.commands.core;

import tech.bosstop.country.commands.CTCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class cmd_stats extends CTCommand {

    public cmd_stats(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ct stats";
    }

    @Override
    public String getPermission() {
        return null;
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
            line = line.replace("%teams%", "&e" + instance.getCountryManager().getCountries().size() + " teams loaded");
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
