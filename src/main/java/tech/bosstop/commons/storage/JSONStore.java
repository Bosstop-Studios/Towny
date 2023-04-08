package tech.bosstop.commons.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.bosstop.commons.structures.storage.CountryFormat;
import tech.bosstop.commons.structures.storage.PlayerFormat;
import tech.bosstop.country.Country;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSONStore {
    private Country instance;
    private Gson gson;

    public JSONStore() {
        this.instance = Country.getInstance();
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }

    private void loadPlayers() throws IOException {
        File file = new File(this.instance.getDataFolder(), "players.json");

        if (!this.instance.getDataFolder().exists())
            this.instance.getDataFolder().mkdirs();
        if (!FileUtil.fileExists(file)) {
            try {
                FileUtil.writeFile(file.getPath(), this.gson.toJson(new PlayerFormat(new ArrayList<>())));
            } finally {
                PlayerFormat playersFormat = this.gson.fromJson(
                        FileUtil.readFile(this.instance.getDataFolder() + "/" + "players.json"), PlayerFormat.class);
                this.instance.getPlayerManager().setPlayers(playersFormat.getPlayers());
            }
        } else {
            PlayerFormat playersFormat = this.gson.fromJson(
                    FileUtil.readFile(this.instance.getDataFolder() + "/" + "players.json"), PlayerFormat.class);
            this.instance.getPlayerManager().setPlayers(playersFormat.getPlayers());
        }

    }

    private void savePlayers() {

        PlayerFormat playersFormat = new PlayerFormat(this.instance.getPlayerManager().getPlayers());
        String json = this.gson.toJson(playersFormat);
        try {
            FileUtil.writeFile(this.instance.getDataFolder() + "/" + "players.json", json);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadTeams() throws IOException {
        File file = new File(this.instance.getDataFolder(), "country.json");

        if (!this.instance.getDataFolder().exists())
            this.instance.getDataFolder().mkdirs();
        if (!FileUtil.fileExists(file)) {
            try {
                FileUtil.writeFile(file.getPath(), this.gson.toJson(new CountryFormat(new ArrayList<>())));
            } finally {
                CountryFormat countryFormat = this.gson.fromJson(
                        FileUtil.readFile(this.instance.getDataFolder() + "/" + "country.json"), CountryFormat.class);
                this.instance.getCountryManager().setCountries(countryFormat.getTeams());
            }
        } else {
            CountryFormat countryFormat = this.gson.fromJson(
                    FileUtil.readFile(this.instance.getDataFolder() + "/" + "country.json"), CountryFormat.class);
            this.instance.getCountryManager().setCountries(countryFormat.getTeams());
        }

    }

    private void saveTeams() {

        CountryFormat countryFormat = new CountryFormat(this.instance.getCountryManager().getCountries());
        String json = this.gson.toJson(countryFormat);
        try {
            FileUtil.writeFile(this.instance.getDataFolder() + "/" + "country.json", json);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void enable() {
        try {
            loadPlayers();
            loadTeams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disable() {
        try {
            savePlayers();
            saveTeams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
