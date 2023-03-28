package ml.bosstop.towny;

import ml.bosstop.commons.storage.JSONStore;
import ml.bosstop.towny.commands.TYCommandHandler;
import ml.bosstop.towny.core.TYPlayerManager;
import ml.bosstop.towny.core.TYTabList;
import ml.bosstop.towny.core.TYTeamManager;
import ml.bosstop.towny.events.TYEventHandler;
import ml.bosstop.towny.utilities.Chat;

import ml.bosstop.towny.utilities.ServerUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Towny extends JavaPlugin {
    private static Towny instance;
    private Chat chat;
    private ServerUtils serverUtils;
    private TYPlayerManager playerManager;
    private TYTeamManager teamManager;
    private TYCommandHandler commandHandler;
    private TYEventHandler eventHandler;
    private TYTabList tyTabList;
    private JSONStore jsonStore;

    @Override
    public void onEnable() {
        instance = this;

        this.chat = new Chat();
        this.serverUtils = new ServerUtils();
        this.playerManager = new TYPlayerManager();
        this.teamManager = new TYTeamManager();
        this.commandHandler = new TYCommandHandler();
        this.eventHandler = new TYEventHandler();
        this.tyTabList = new TYTabList();
        this.jsonStore = new JSONStore();

        try {
            this.jsonStore.enable();
            this.tyTabList.enable();
            this.eventHandler.register();
            this.commandHandler.registerCommands();
        } catch (Exception e) {
            this.chat.console(e.getMessage());
            e.printStackTrace();
        } finally {
            this.chat.console("&aTowny enabled!");
        }

    }

    @Override
    public void onDisable() {
        try {
            this.jsonStore.disable();
            this.tyTabList.disable();
        } catch (Exception e) {
            this.chat.console("&cFailed to disable Towny!");
            this.chat.console(e.getMessage());
        } finally {
            this.chat.console("&aTowny disabled!");
        }
    }

    public Chat getChat() {
        return this.chat;
    }

    public ServerUtils getServerUtils() {
        return this.serverUtils;
    }

    public TYPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public TYTeamManager getTeamManager() {
        return this.teamManager;
    }

    public TYCommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    public static Towny getInstance() {
        return instance;
    }

}
