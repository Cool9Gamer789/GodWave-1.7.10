package net.Jack_Black12.wave.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.Jack_Black12.wave.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldSettings;

public class ModEvents {
    @SubscribeEvent
    public void onHoldingGodWave(TickEvent.PlayerTickEvent event) {
        if (event.player.worldObj.isRemote) {
            return;
        }

        // Assume the player doesn't have the GodWave
        boolean hasGodWave = false;

        // Check all inventory slots (main inventory + hotbar + armor + offhand if applicable)
        for (int i = 0; i < event.player.inventory.getSizeInventory(); i++) {
            ItemStack itemS = event.player.inventory.getStackInSlot(i);
            if (itemS != null && itemS.getItem() == ModItems.godWaveItem) {
                hasGodWave = true;
                break;
            }
        }

        if (hasGodWave) {
            // If they don't have the effect, add it.
            // The arguments for PotionEffect are:
            // 1. Potion.EFFECT_YOU_WANT.id: The potion ID to apply.
            // 2. Integer.MAX_VALUE: The duration in ticks. 20 ticks = 1 second.
            // 3. 4: The amplifier (strength). 0 is Level 1, 1 is Level 2, and so on.
            // 4. false: 'ambient' - whether the effect is from an ambient source (like a beacon).

            // Regeneration 5
            if (!event.player.isPotionActive(Potion.regeneration)) {
                event.player.addPotionEffect(new PotionEffect(Potion.regeneration.id, Integer.MAX_VALUE, 4, false));
            }
            // Resistance 5
            if (!event.player.isPotionActive(Potion.resistance)) {
                event.player.addPotionEffect(new PotionEffect(Potion.resistance.id, Integer.MAX_VALUE, 4, false));
            }
            // Strength 18
            if (!event.player.isPotionActive(Potion.damageBoost)) {
                event.player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, 17, false));
            }
            // Fire Resistance 5
            if (!event.player.isPotionActive(Potion.fireResistance)) {
                event.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 4, false));
            }
            // Water Breathing 5
            if (!event.player.isPotionActive(Potion.waterBreathing)) {
                event.player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, Integer.MAX_VALUE, 4, false));
            }
            // Jump Boost 2
            if (!event.player.isPotionActive(Potion.jump)) {
                event.player.addPotionEffect(new PotionEffect(Potion.jump.id, Integer.MAX_VALUE, 1, false));
            }
            // Speed 3
            if (!event.player.isPotionActive(Potion.moveSpeed)) {
                event.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, Integer.MAX_VALUE, 2, false));
            }
            // Cast the player as an EntityPlayerMP (server player in 1.7.10)
            if (event.player instanceof EntityPlayerMP) {
                EntityPlayerMP serverPlayer = (EntityPlayerMP) event.player;
                if (serverPlayer.theItemInWorldManager.getGameType() == WorldSettings.GameType.SURVIVAL) {
                    // Give the player the ability to fly
                    serverPlayer.capabilities.allowFlying = true;
                    serverPlayer.sendPlayerAbilities();
                }
            }
        } else {
            if (event.player instanceof EntityPlayerMP) {
                EntityPlayerMP serverPlayer = (EntityPlayerMP) event.player;
                if (serverPlayer.theItemInWorldManager.getGameType() == WorldSettings.GameType.SURVIVAL) {
                    // Take away the player's ability to fly
                    serverPlayer.capabilities.allowFlying = false;
                    serverPlayer.capabilities.isFlying = false; // Also stop them from flying
                    serverPlayer.sendPlayerAbilities();
                }
            }
        }
    }
}
