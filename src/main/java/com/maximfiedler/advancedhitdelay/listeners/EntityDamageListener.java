package com.maximfiedler.advancedhitdelay.listeners;

import com.maximfiedler.advancedhitdelay.AdvancedHitDelay;
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
            if(this.advancedHitDelay.config.isNoDamageDelay()) {
                livingEntity.setMaximumNoDamageTicks(0);
                livingEntity.setNoDamageTicks(0);
            }
            event.setDamage(event.getDamage() * advancedHitDelay.config.getDamageMultiplier());
        }
    }

}
