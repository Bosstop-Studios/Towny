package tech.bosstop.commons.structures.storage;

public class ConfigFormat {
    boolean Tablist = true;

    public ConfigFormat(boolean tablist) {
        this.Tablist = tablist;
    }

    public boolean isTablistEnabled() {
        return this.Tablist;
    }

    public void setTablist(boolean tablist) {
        this.Tablist = tablist;
    }
}
