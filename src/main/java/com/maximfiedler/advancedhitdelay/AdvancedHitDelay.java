package com.maximfiedler.advancedhitdelay;

import com.maximfiedler.advancedhitdelay.commands.HitDelayCommand;
import com.maximfiedler.advancedhitdelay.commands.HitDelayTabCompleter;
import com.maximfiedler.advancedhitdelay.listeners.EntityDamageListener;
import com.maximfiedler.advancedhitdelay.listeners.PlayerJoinListener;
import com.maximfiedler.advancedhitdelay.utils.Config;
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
