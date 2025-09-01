package net.Jack_Black12.wave;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.Jack_Black12.wave.events.ModEvents;
import net.Jack_Black12.wave.item.ModItems;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = GodWave.MODID, version = GodWave.VERSION)
public class GodWave
{
    public static final String MODID = "wave";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Register items in preInit phase
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register with MinecraftForge EVENT_BUS (for most game events)
        MinecraftForge.EVENT_BUS.register(new ModEvents());

        // Also register with FML EVENT_BUS (for FML-specific events like TickEvent)
        FMLCommonHandler.instance().bus().register(new ModEvents());

    }
}
