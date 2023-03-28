package ml.bosstop.commons.structures.storage;

import ml.bosstop.commons.structures.core.TYTeam;

import java.util.List;

public class TeamFormat {
    List<TYTeam> teams;

    public TeamFormat(List<TYTeam> teams) {
        this.teams = teams;
    }

    public List<TYTeam> getTeams() {
        return this.teams;
    }

    public void setTeams(List<TYTeam> teams) {
        this.teams = teams;
    }
}
