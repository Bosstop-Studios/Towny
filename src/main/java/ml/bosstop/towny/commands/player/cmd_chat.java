package ml.bosstop.towny.commands.player;

import ml.bosstop.commons.structures.core.TYChat;
import ml.bosstop.towny.commands.TYCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class cmd_chat extends TYCommand {

    public cmd_chat(String command) {
        super(command);
    }

    @Override
    public String getUsage() {
        return "/ty chat <global/team>";
    }

    @Override
    public String getPermission() {
        return "towny.chat";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(argsNeeded(sender, args, getUsage(), 1)) return true;

        Player player = Bukkit.getPlayer(sender.getName());
        assert player != null;
        if(args[1].equalsIgnoreCase("global")) {
            instance.getPlayerManager().getPlayer(player.getUniqueId()).setChat(TYChat.GLOBAL);
            instance.getChat().send(sender, "&aYou have set your chat to global.");
        } else if(args[1].equalsIgnoreCase("team")) {
            instance.getPlayerManager().getPlayer(player.getUniqueId()).setChat(TYChat.TEAM);
            instance.getChat().send(sender, "&aYou have set your chat to team.");
        } else {
            instance.getChat().send(sender, "&cInvalid chat type.");
        }

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabList = new ArrayList<>();
        if(args.length == 1) {
            tabList.add("global");
            tabList.add("team");
        }
        return tabList;
    }
}
