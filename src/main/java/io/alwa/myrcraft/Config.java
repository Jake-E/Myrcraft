package io.alwa.myrcraft;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config
{
    public static final String CATEGORY_GENERAL = "general";
    
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;
    
    public static ForgeConfigSpec.IntValue TREE_CHANCE;
    public static ForgeConfigSpec.IntValue LATEX_MULTIPLIER;
    public static ForgeConfigSpec.IntValue TRANSFER_RATE;
    public static ForgeConfigSpec.IntValue TRANSFER_TICKS;
    
    static
    {
        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        COMMON_BUILDER.pop();
    
        TREE_CHANCE = COMMON_BUILDER.comment("Chance for a tree to spawn in a chunk")
                .defineInRange("treeChance", 3, 0, Integer.MAX_VALUE);
        LATEX_MULTIPLIER = COMMON_BUILDER.comment("Amount of latex per rubber wood log")
                .defineInRange("latexMultiplier ", 1000, 0, Integer.MAX_VALUE);
        TRANSFER_RATE = COMMON_BUILDER.comment("Rate at which latext seeps out of log in mB")
                .defineInRange("transferRate", 10, 1, Integer.MAX_VALUE);
        TRANSFER_TICKS = COMMON_BUILDER.comment("Time between each seep of latex")
                .defineInRange("transferTicks", 20, 1, Integer.MAX_VALUE);
        
        
        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
    
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        
        configData.load();
        spec.setConfig(configData);
    }
    
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {}
    
    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {}
}
