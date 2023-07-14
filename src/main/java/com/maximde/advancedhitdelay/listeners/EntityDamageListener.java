package com.maximde.advancedhitdelay.listeners;

import com.maximde.advancedhitdelay.AdvancedHitDelay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {
    private AdvancedHitDelay advancedHitDelay;

    public EntityDamageListener(AdvancedHitDelay advancedHitDelay) {
        this.advancedHitDelay = advancedHitDelay;
    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            livingEntity.setMaximumNoDamageTicks((int) advancedHitDelay.config.getMaxNoDamageTicks());
            livingEntity.setNoDamageTicks((int) advancedHitDelay.config.getNoDamageTicks());
            event.setDamage(event.getDamage() * advancedHitDelay.config.getDamageMultiplier());
        }
    }

}
