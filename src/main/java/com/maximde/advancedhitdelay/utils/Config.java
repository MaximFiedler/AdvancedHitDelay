package com.maximde.advancedhitdelay.utils;

import com.maximde.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Config {
    private double damageMultiplier;
    private double noDamageTicks;
    private double maxNoDamageTicks;
    private boolean noHitDelay;
    private boolean noDamageDelay;
    private AdvancedHitDelay advancedHitDelay;
    private final File file = new File("plugins/AdvancedHitDelay", "config.yml");
    private YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);

    public Config(AdvancedHitDelay advancedHitDelay) {
        this.advancedHitDelay = advancedHitDelay;
        initConfig();
        initValues();
    }

    public void reload() {
        this.cfg = new YamlConfiguration().loadConfiguration(file);
        initValues();
    }

    private void initConfig() {
        setValueIfNotSet("Options.NoHitDelay", true);
        setValueIfNotSet("Options.NoDamageDelay", false);
        setValueIfNotSet("Options.DamageMultiplier", 1);
        setValueIfNotSet("Options.NoDamageTicks", 10);
        setValueIfNotSet("Options.MaxNoDamageTicks", 10);
        saveConfig();
    }

    public void updateValue(String path, Object value) {
        this.cfg.set(path, value);
        saveConfig();
        initValues();
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(this.advancedHitDelay.config.isNoHitDelay() ? 1024.0D : this.advancedHitDelay.config.getHitDelay());
            all.saveData();
        }
    }

    private void initValues() {
        damageMultiplier = this.cfg.getDouble("Options.DamageMultiplier");
        noDamageTicks = this.cfg.getDouble("Options.NoDamageTicks");
        maxNoDamageTicks = this.cfg.getDouble("Options.MaxNoDamageTicks");
        noHitDelay = this.cfg.getBoolean("Options.NoHitDelay");
        noDamageDelay = this.cfg.getBoolean("Options.NoDamageDelay");
    }

    private void setValueIfNotSet(String path, Object value) {
        if(!this.cfg.isSet(path)) cfg.set(path, value);
    }

    public double getHitDelay() {
        return hitDelay;
    }

    private double hitDelay;

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public double getNoDamageTicks() {
        return noDamageTicks;
    }

    public double getMaxNoDamageTicks() {
        return maxNoDamageTicks;
    }

    public boolean isNoHitDelay() {
        return noHitDelay;
    }

    public boolean isNoDamageDelay() {
        return noDamageDelay;
    }

    public void saveConfig() {
        try {
            this.cfg.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
