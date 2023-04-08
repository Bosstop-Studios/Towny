package tech.bosstop.country.core;

import tech.bosstop.commons.structures.core.CTPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CTPlayerManager {
    private List<CTPlayer> ctPlayers = new ArrayList<>();

    public List<CTPlayer> getPlayers() {
        return this.ctPlayers;
    }

    public CTPlayer getPlayer(UUID uuid) {
        for (CTPlayer ctPlayer : this.ctPlayers) {
            if (ctPlayer.getUuid().equals(uuid))
                return ctPlayer;
        }
        return null;
    }

    public void addPlayer(CTPlayer ctPlayer) {
        this.ctPlayers.add(ctPlayer);
    }

    public void removePlayer(CTPlayer ctPlayer) {
        this.ctPlayers.remove(ctPlayer);
    }

    public void setPlayers(List<CTPlayer> ctPlayers) {
        this.ctPlayers = ctPlayers;
    }
}
