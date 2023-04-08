package tech.bosstop.country.core;

import tech.bosstop.commons.structures.core.CTCountry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CTCountryManager {
    private List<CTCountry> ctCountries = new ArrayList<>();
    private HashMap<UUID, String> ctCountryInvites = new HashMap<>();

    public List<CTCountry> getCountries() {
        return this.ctCountries;
    }

    public CTCountry getCountry(String name) {
        for (CTCountry ctCountry : this.ctCountries) {
            if (ctCountry.getName().equalsIgnoreCase(name))
                return ctCountry;
        }
        return null;
    }

    public void addCountry(CTCountry ctCountry) {
        this.ctCountries.add(ctCountry);
    }

    public void removeCountry(CTCountry ctCountry) {
        this.ctCountries.remove(ctCountry);
    }

    public void removeCountry(String name) {
        this.ctCountries.remove(this.getCountry(name));
    }

    public HashMap<UUID, String> getCountryInvites() {
        return this.ctCountryInvites;
    }

    public String getCountryInvite(UUID uuid) {
        return this.ctCountryInvites.get(uuid);
    }

    public void addCountryInvite(UUID uuid, String countryName) {
        this.ctCountryInvites.put(uuid, countryName);
    }

    public void removeCountryInvite(UUID uuid) {
        this.ctCountryInvites.remove(uuid);
    }

    public void setCountries(List<CTCountry> ctCountries) {
        this.ctCountries = ctCountries;
    }
}
