package ml.bosstop.towny.core;

import com.google.common.collect.ImmutableMap;
import ml.bosstop.commons.structures.core.TYPlayer;
import ml.bosstop.towny.Towny;
import ml.bosstop.towny.utilities.PowerFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TYTabList {
    private Towny instance = Towny.getInstance();
    private int taskID;
    private String tabListHeader() {

        StringBuilder header = new StringBuilder();

        String format = "[team] [player]";


        Bukkit.getOnlinePlayers().forEach(player -> {
            TYPlayer tyPlayer = instance.getPlayerManager().getPlayer(player.getUniqueId());
            String teamPrefix = tyPlayer.getTeam();

            String finalTeamPrefix = "";
            if (teamPrefix != null) finalTeamPrefix = teamPrefix;

            String formatted = PowerFormatter.format(format, ImmutableMap.of("team", finalTeamPrefix, "player", player.getDisplayName()), '[', ']');
            header.append(formatted);
        });

        return header.toString();
    }

    private void updateTabList() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListName(instance.getChat().colorize(tabListHeader()));
        }
    }

    public void enable() {
        taskID = Bukkit.getScheduler().runTaskTimer(instance, this::updateTabList, 0L, 20L).getTaskId();
    }

    public void disable() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
