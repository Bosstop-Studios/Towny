package ml.bosstop.towny.core;

import ml.bosstop.commons.structures.core.TYTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TYTeamManager {
    private List<TYTeam> tyTeams = new ArrayList<>();
    private HashMap<UUID, String> tyTeamInvites = new HashMap<>();

    public List<TYTeam> getTeams() {
        return this.tyTeams;
    }

    public TYTeam getTeam(String name) {
        for (TYTeam tyTeam : this.tyTeams) {
            if (tyTeam.getName().equalsIgnoreCase(name))
                return tyTeam;
        }
        return null;
    }

    public void addTeam(TYTeam tyTeam) {
        this.tyTeams.add(tyTeam);
    }

    public void removeTeam(TYTeam tyTeam) {
        this.tyTeams.remove(tyTeam);
    }

    public void removeTeam(String name) {
        this.tyTeams.remove(getTeam(name));
    }

    public HashMap<UUID, String> getTeamInvites() {
        return this.tyTeamInvites;
    }

    public String getTeamInvite(UUID uuid) {
        return this.tyTeamInvites.get(uuid);
    }

    public void addTeamInvite(UUID uuid, String teamName) {
        this.tyTeamInvites.put(uuid, teamName);
    }

    public void removeTeamInvite(UUID uuid) {
        this.tyTeamInvites.remove(uuid);
    }

    public void setTeams(List<TYTeam> tyTeams) {
        this.tyTeams = tyTeams;
    }
}
