package io.alwa.myrcraft;

import io.alwa.myrcraft.items.MyrcraftItems;
import io.alwa.myrcraft.world.WorldGenHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Myrcraft.MODID, name = Myrcraft.NAME, version = Myrcraft.VERSION)
public class Myrcraft {
    public static final String MODID = "myrcraft";
    public static final String NAME = "Myrcraft";
    public static final String VERSION = "1.0";

    private static Logger logger;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    public static final Fluid LATEX = new Fluid("latex", new ResourceLocation(MODID, "block/latex"), new ResourceLocation(MODID, "block/latex")) {
    }.setUnlocalizedName("latex.name");

    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MyrcraftItems.WOODBUCKET);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();

        FluidRegistry.registerFluid(LATEX);
        FluidRegistry.addBucketForFluid(LATEX);
        MinecraftForge.TERRAIN_GEN_BUS.register(WorldGenHandler.class);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

}
