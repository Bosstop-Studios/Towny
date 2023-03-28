package ml.bosstop.commons.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ml.bosstop.commons.structures.storage.PlayerFormat;
import ml.bosstop.commons.structures.storage.TeamFormat;
import ml.bosstop.towny.Towny;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSONStore {
    private Towny instance;
    private Gson gson;

    public JSONStore() {
        this.instance = Towny.getInstance();
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
        File file = new File(this.instance.getDataFolder(), "teams.json");

        if (!this.instance.getDataFolder().exists())
            this.instance.getDataFolder().mkdirs();
        if (!FileUtil.fileExists(file)) {
            try {
                FileUtil.writeFile(file.getPath(), this.gson.toJson(new TeamFormat(new ArrayList<>())));
            } finally {
                TeamFormat teamFormat = this.gson.fromJson(
                        FileUtil.readFile(this.instance.getDataFolder() + "/" + "teams.json"), TeamFormat.class);
                this.instance.getTeamManager().setTeams(teamFormat.getTeams());
            }
        } else {
            TeamFormat teamFormat = this.gson.fromJson(
                    FileUtil.readFile(this.instance.getDataFolder() + "/" + "teams.json"), TeamFormat.class);
            this.instance.getTeamManager().setTeams(teamFormat.getTeams());
        }

    }

    private void saveTeams() {

        TeamFormat teamFormat = new TeamFormat(this.instance.getTeamManager().getTeams());
        String json = this.gson.toJson(teamFormat);
        try {
            FileUtil.writeFile(this.instance.getDataFolder() + "/" + "teams.json", json);
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
