package ml.bosstop.towny.core;

import ml.bosstop.commons.structures.core.TYPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TYPlayerManager {
    private List<TYPlayer> tyPlayers = new ArrayList<>();

    public List<TYPlayer> getPlayers() {
        return this.tyPlayers;
    }

    public TYPlayer getPlayer(UUID uuid) {
        for (TYPlayer tyPlayer : tyPlayers) {
            if (tyPlayer.getUuid().equals(uuid))
                return tyPlayer;
        }
        return null;
    }

    public void addPlayer(TYPlayer tyPlayer) {
        tyPlayers.add(tyPlayer);
    }

    public void removePlayer(TYPlayer tyPlayer) {
        tyPlayers.remove(tyPlayer);
    }

    public void setPlayers(List<TYPlayer> tyPlayers) {
        this.tyPlayers = tyPlayers;
    }
}
