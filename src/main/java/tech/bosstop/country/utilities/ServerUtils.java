package tech.bosstop.country.utilities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerUtils {
    private String getServerVersion() {
        return Bukkit.getServer().getVersion();
    }

    private String getServerName() {
        return Bukkit.getServer().getName();
    }

    public String getServerInfo() {
        return "Server: " + getServerName() + " " + getServerVersion();
    }

    public String getServerPlugins() {
        String pluginsList = "";
        Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
        Arrays.stream(plugins).map(Plugin::getName).forEach(plugin -> pluginsList.concat(plugin + ", "));
        return pluginsList;
    }

    public List<String> getPlayerList() {
        List<String> playerList = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> playerList.add(player.getName()));
        return playerList;
    }

}
