package tech.bosstop.country.events;

import tech.bosstop.commons.structures.core.CTPlayer;
import tech.bosstop.country.Country;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {

    private final Country instance = Country.getInstance();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        CTPlayer ctPlayer = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        if(ctPlayer == null) {
            CTPlayer newPlayer = new CTPlayer(event.getPlayer().getUniqueId(), null);
            instance.getPlayerManager().addPlayer(newPlayer);
            instance.getChat().console("Added player " + event.getPlayer().getName() + " to Country Player List");
        }

    }
}
