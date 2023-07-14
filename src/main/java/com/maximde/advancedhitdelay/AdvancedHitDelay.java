package com.maximde.advancedhitdelay;

import com.maximde.advancedhitdelay.commands.HitDelayCommand;
import com.maximde.advancedhitdelay.commands.HitDelayTabCompleter;
import com.maximde.advancedhitdelay.listeners.EntityDamageListener;
import com.maximde.advancedhitdelay.listeners.PlayerJoinListener;
import com.maximde.advancedhitdelay.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedHitDelay extends JavaPlugin {

    public Config config;
    
    @Override
    public void onEnable() {
        this.config = new Config(this);
        registerListeners();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("hitdelay").setExecutor(new HitDelayCommand(this));
        getCommand("hitdelay").setTabCompleter(new HitDelayTabCompleter(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

}
