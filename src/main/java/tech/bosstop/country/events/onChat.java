package tech.bosstop.country.events;

import com.google.common.collect.ImmutableMap;
import tech.bosstop.commons.structures.core.CTChat;
import tech.bosstop.commons.structures.core.CTCountry;
import tech.bosstop.commons.structures.core.CTPlayer;
import tech.bosstop.country.Country;
import tech.bosstop.country.utilities.PowerFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class onChat implements Listener {
    private final Country instance = Country.getInstance();

    private List<Player> getPlayersInCountry(CTCountry team) {
        List<Player> players = new ArrayList<>();
        for (CTPlayer ctPlayer : instance.getPlayerManager().getPlayers()) {
            if (ctPlayer.getCountry().equalsIgnoreCase(team.getName()))
                players.add(Bukkit.getPlayer(ctPlayer.getUuid()));
        }
        return players;
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {

        String format = "[country] [player] >> [message]";
        String countryPrefix;
        String finalCountryPrefix = "";

        CTPlayer ctPlayer = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

        if (ctPlayer == null)
            return;

        try {
            countryPrefix = instance.getCountryManager().getCountry(ctPlayer.getCountry()).getPrefix();
            if (countryPrefix != null) finalCountryPrefix = countryPrefix;
        } finally {

            format = PowerFormatter.format(format, ImmutableMap.<String, String>builder()
                    .put("country", finalCountryPrefix)
                    .put("player", event.getPlayer().getDisplayName())
                    .put("message", event.getMessage())
                    .build(), '[', ']');

            if(ctPlayer.getChat() == CTChat.TEAM) {
                if(ctPlayer.getCountry() == null) {
                    event.setFormat(instance.getChat().colorize(format));
                }
                for (Player player : getPlayersInCountry(instance.getCountryManager().getCountry(ctPlayer.getCountry()))) {
                    instance.getChat().sendRaw(player, format);
                }
                event.setCancelled(true);
            } else {
                event.setFormat(instance.getChat().colorize(format));
            }
        }

    }

}
