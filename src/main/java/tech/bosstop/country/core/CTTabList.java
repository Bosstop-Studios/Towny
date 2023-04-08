package tech.bosstop.country.core;

import com.google.common.collect.ImmutableMap;
import tech.bosstop.commons.structures.core.CTPlayer;
import tech.bosstop.country.Country;
import tech.bosstop.country.utilities.PowerFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CTTabList {
    private Country instance = Country.getInstance();
    private int taskID;
    private String tabListHeader() {

        StringBuilder header = new StringBuilder();

        String format = "[country] [player]";


        Bukkit.getOnlinePlayers().forEach(player -> {
            CTPlayer ctPlayer = instance.getPlayerManager().getPlayer(player.getUniqueId());
            String countryPrefix = ctPlayer.getCountry();

            String finalCountryPrefix = "";
            if (countryPrefix != null) finalCountryPrefix = countryPrefix;

            String formatted = PowerFormatter.format(format, ImmutableMap.of("country", finalCountryPrefix, "player", player.getDisplayName()), '[', ']');
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
