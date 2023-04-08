package tech.bosstop.country;

import tech.bosstop.commons.storage.JSONStore;
import tech.bosstop.country.commands.CTCommandHandler;
import tech.bosstop.country.core.CTPlayerManager;
import tech.bosstop.country.core.CTTabList;
import tech.bosstop.country.core.CTCountryManager;
import tech.bosstop.country.events.CTEventHandler;
import tech.bosstop.country.utilities.Chat;
import tech.bosstop.country.utilities.ServerUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Country extends JavaPlugin {
    private static Country instance;
    private Chat chat;
    private ServerUtils serverUtils;
    private CTPlayerManager playerManager;
    private CTCountryManager teamManager;
    private CTCommandHandler commandHandler;
    private CTEventHandler eventHandler;
    private CTTabList CTTabList;
    private JSONStore jsonStore;

    @Override
    public void onEnable() {
        instance = this;

        this.chat = new Chat();
        this.serverUtils = new ServerUtils();
        this.playerManager = new CTPlayerManager();
        this.teamManager = new CTCountryManager();
        this.commandHandler = new CTCommandHandler();
        this.eventHandler = new CTEventHandler();
        this.CTTabList = new CTTabList();
        this.jsonStore = new JSONStore();

        try {
            this.jsonStore.enable();
            this.CTTabList.enable();
            this.eventHandler.register();
            this.commandHandler.registerCommands();
        } catch (Exception e) {
            this.chat.console(e.getMessage());
            e.printStackTrace();
        } finally {
            this.chat.console("&aCountry enabled!");
        }

    }

    @Override
    public void onDisable() {
        try {
            this.jsonStore.disable();
            this.CTTabList.disable();
        } catch (Exception e) {
            this.chat.console("&cFailed to disable Country!");
            this.chat.console(e.getMessage());
        } finally {
            this.chat.console("&aCountry disabled!");
        }
    }

    public Chat getChat() {
        return this.chat;
    }

    public ServerUtils getServerUtils() {
        return this.serverUtils;
    }

    public CTPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public CTCountryManager getCountryManager() {
        return this.teamManager;
    }

    public CTCommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    public static Country getInstance() {
        return instance;
    }

}
