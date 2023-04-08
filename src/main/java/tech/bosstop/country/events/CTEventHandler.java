package tech.bosstop.country.events;

import tech.bosstop.country.Country;
import org.bukkit.Bukkit;

public class CTEventHandler {
    private Country instance;

    public CTEventHandler() {
        this.instance = Country.getInstance();
    }
    public void register() {
        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), instance);
        Bukkit.getPluginManager().registerEvents(new onChat(), instance);
    }
}
