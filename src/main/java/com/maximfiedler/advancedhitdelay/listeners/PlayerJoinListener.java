package com.maximfiedler.advancedhitdelay.listeners;

import com.maximfiedler.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private AdvancedHitDelay advancedHitDelay;

    public PlayerJoinListener(AdvancedHitDelay advancedHitDelay) {
        this.advancedHitDelay = advancedHitDelay;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(this.advancedHitDelay.config.isNoHitDelay() ? 1024.0D : this.advancedHitDelay.config.getHitDelay()); // Default value 4.0D
        player.saveData();
    }
}
