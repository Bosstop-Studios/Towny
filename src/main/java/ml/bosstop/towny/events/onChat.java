package ml.bosstop.towny.events;

import com.google.common.collect.ImmutableMap;
import ml.bosstop.commons.structures.core.TYChat;
import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.commons.structures.core.TYTeam;
import ml.bosstop.towny.Towny;
import ml.bosstop.towny.utilities.PowerFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class onChat implements Listener {
    private final Towny instance = Towny.getInstance();

    private List<Player> getPlayersInTeam(TYTeam team) {
        List<Player> players = new ArrayList<>();
        for (TYPlayer tyPlayer : instance.getPlayerManager().getPlayers()) {
            if (tyPlayer.getTeam().equalsIgnoreCase(team.getName()))
                players.add(Bukkit.getPlayer(tyPlayer.getUuid()));
        }
        return players;
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {

        String format = "[team] [player] >> [message]";
        String teamPrefix;
        String finalTeamPrefix = "";

        TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

        if (tyPlayer == null)
            return;

        try {
            teamPrefix = instance.getTeamManager().getTeam(tyPlayer.getTeam()).getPrefix();
            if (teamPrefix != null) finalTeamPrefix = teamPrefix;
        } finally {

            format = PowerFormatter.format(format, ImmutableMap.<String, String>builder()
                    .put("team", finalTeamPrefix)
                    .put("player", event.getPlayer().getDisplayName())
                    .put("message", event.getMessage())
                    .build(), '[', ']');

            if(tyPlayer.getChat() == TYChat.TEAM) {
                if(tyPlayer.getTeam() == null) {
                    event.setFormat(instance.getChat().colorize(format));
                }
                for (Player player : getPlayersInTeam(instance.getTeamManager().getTeam(tyPlayer.getTeam()))) {
                    instance.getChat().sendRaw(player, format);
                }
                event.setCancelled(true);
            } else {
                event.setFormat(instance.getChat().colorize(format));
            }
        }

    }

}
