package ml.bosstop.commons.structures.storage;

import ml.bosstop.commons.structures.core.TYPlayer;

import java.util.List;

public class PlayerFormat {
    List<TYPlayer> players;

    public PlayerFormat(List<TYPlayer> players) {
        this.players = players;
    }

    public List<TYPlayer> getPlayers() {
        return players;
    }
}