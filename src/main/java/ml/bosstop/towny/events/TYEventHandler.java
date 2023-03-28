package ml.bosstop.towny.events;

import ml.bosstop.towny.Towny;
import org.bukkit.Bukkit;

public class TYEventHandler {
    private Towny instance;

    public TYEventHandler() {
        this.instance = Towny.getInstance();
    }
    public void register() {
        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), instance);
        Bukkit.getPluginManager().registerEvents(new onChat(), instance);
    }
}
