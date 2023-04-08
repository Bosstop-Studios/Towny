package tech.bosstop.commons.structures.core;

import java.util.UUID;

public class CTPlayer {
    private UUID uuid;
    private CTChat chat = CTChat.GLOBAL;
    private String country;

    public CTPlayer(UUID uuid, String country) {
        this.uuid = uuid;
        this.country = country;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String team) {
        this.country = team;
    }

    public boolean hasCountry() {
        return this.country != null;
    }

    public boolean isInCountry(String team) {
        return this.country.equals(team);
    }

    public CTChat getChat() {
        return this.chat;
    }

    public void setChat(CTChat chat) {
        this.chat = chat;
    }

}
