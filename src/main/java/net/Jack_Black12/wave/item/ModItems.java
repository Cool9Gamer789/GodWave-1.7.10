package net.Jack_Black12.wave.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
    public static Item godWaveItem;

    public static void init() {
        godWaveItem = new Item()
                .setUnlocalizedName("godwave")
                .setTextureName("wave:certerium")
                .setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerItem(godWaveItem, godWaveItem.getUnlocalizedName());
    }
}
