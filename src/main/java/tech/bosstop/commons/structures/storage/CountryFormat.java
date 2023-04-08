package tech.bosstop.commons.structures.storage;

import tech.bosstop.commons.structures.core.CTCountry;

import java.util.List;

public class CountryFormat {
    List<CTCountry> teams;

    public CountryFormat(List<CTCountry> teams) {
        this.teams = teams;
    }

    public List<CTCountry> getTeams() {
        return this.teams;
    }

    public void setTeams(List<CTCountry> teams) {
        this.teams = teams;
    }
}
