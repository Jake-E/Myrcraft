package io.alwa.myrcraft;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Myrcraft.MODID)
@Config(modid = Myrcraft.MODID, category = "")
public class MyrcraftConfig {

    public static class RubberTree {
        @Config.RangeInt(min = 0)
        @Config.Comment("Chance for a tree to spawn in a chunk")
        public int tree_chance = 3;

        @Config.RangeInt(min = 0)
        @Config.Comment("Amount of latex per rubber wood log")
        public int latex_multiplier = 1000;

        @Config.RangeInt(min = 1)
        @Config.Comment("Rate at which latext seeps out of log in mB")
        public int transfer_rate = 10;

        @Config.RangeInt(min = 1)
        @Config.Comment("Time between each seep of latex")
        public int transfer_ticks = 20;

    }

    public static final RubberTree rubbertree = new RubberTree();

    public static boolean sync() {
        ConfigManager.sync(Myrcraft.MODID, Config.Type.INSTANCE);
        return true;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Myrcraft.MODID)) {
            sync();
        }
    }
}
