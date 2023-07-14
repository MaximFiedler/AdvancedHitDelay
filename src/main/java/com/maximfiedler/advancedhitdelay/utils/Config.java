package com.maximfiedler.advancedhitdelay.utils;

import com.maximfiedler.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Config {
    private int damageMultiplier;
    private int noDamageTicks;
    private int maxNoDamageTicks;
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
        damageMultiplier = this.cfg.getInt("Options.DamageMultiplier");
        noDamageTicks = this.cfg.getInt("Options.NoDamageTicks");
        maxNoDamageTicks = this.cfg.getInt("Options.MaxNoDamageTicks");
        noHitDelay = this.cfg.getBoolean("Options.NoHitDelay");
        noDamageDelay = this.cfg.getBoolean("Options.NoDamageDelay");
    }

    private void setValueIfNotSet(String path, Object value) {
        if(!this.cfg.isSet(path)) cfg.set(path, value);
    }

    public int getHitDelay() {
        return hitDelay;
    }

    private int hitDelay;

    public int getDamageMultiplier() {
        return damageMultiplier;
    }

    public int getNoDamageTicks() {
        return noDamageTicks;
    }

    public int getMaxNoDamageTicks() {
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
