package tech.bosstop.commons.structures.storage;

import tech.bosstop.commons.structures.core.CTPlayer;

import java.util.List;

public class PlayerFormat {
    List<CTPlayer> players;

    public PlayerFormat(List<CTPlayer> players) {
        this.players = players;
    }

    public List<CTPlayer> getPlayers() {
        return players;
    }
}