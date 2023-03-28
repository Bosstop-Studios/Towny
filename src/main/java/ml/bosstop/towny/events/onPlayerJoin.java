package ml.bosstop.towny.events;

import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.towny.Towny;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class onPlayerJoin implements Listener {

    private final Towny instance = Towny.getInstance();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        if(tyPlayer == null) {
            TYPlayer newPlayer = new TYPlayer(event.getPlayer().getUniqueId(), null);
            instance.getPlayerManager().addPlayer(newPlayer);
            instance.getChat().console("Added player " + event.getPlayer().getName() + " to PowerRanks Player List");
        }

    }
}
