package ml.bosstop.commons.structures.core;

import java.util.UUID;

public class TYPlayer {
    private UUID uuid;
    private TYChat chat = TYChat.GLOBAL;
    private String team;

    public TYPlayer(UUID uuid, String team) {
        this.uuid = uuid;
        this.team = team;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public boolean hasTeam() {
        return this.team != null;
    }

    public boolean isOnTeam(String team) {
        return this.team.equals(team);
    }

    public TYChat getChat() {
        return this.chat;
    }

    public void setChat(TYChat chat) {
        this.chat = chat;
    }

}
