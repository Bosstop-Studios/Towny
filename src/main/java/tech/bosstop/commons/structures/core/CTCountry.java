package tech.bosstop.commons.structures.core;

import java.util.UUID;

public class CTCountry {
    private String name;
    private String prefix;
    private UUID leader;
    private UUID coLeader;

    public CTCountry(String name, String prefix, UUID leader) {
        this.name = name;
        this.prefix = prefix;
        this.leader = leader;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public UUID getLeader() {
        return this.leader;
    }

    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public UUID getCoLeader() {
        return this.coLeader;
    }

    public void setCoLeader(UUID coLeader) {
        this.coLeader = coLeader;
    }

}
