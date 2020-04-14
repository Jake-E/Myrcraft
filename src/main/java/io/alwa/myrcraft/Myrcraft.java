package io.alwa.myrcraft;

import io.alwa.myrcraft.blocks.*;
import io.alwa.myrcraft.entity.MyrcraftEntities;
import io.alwa.myrcraft.fluids.MyrcraftFluids;
import io.alwa.myrcraft.items.MyrcraftItems;
import io.alwa.myrcraft.proxy.ClientProxy;
import io.alwa.myrcraft.proxy.CommonProxy;
import io.alwa.myrcraft.tiles.TileEntityWoodBucket;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Myrcraft.MOD_ID)
public class Myrcraft {

    public static final String MOD_ID = "myrcraft";
    private static Logger LOGGER = LogManager.getLogger();
    public final Myrcraft instance;
    public final CommonProxy proxy;
    public static final ItemGroup itemGroup  = new ItemGroup("myrcraft") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(MyrcraftItems.RUBBER_WOOD_ITEM.get());
        }
    };

    public Myrcraft() {
        instance = this;
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MyrcraftItems.ITEMS.register(eventBus);
        MyrcraftBlocks.BLOCKS.register(eventBus);
        MyrcraftBlocks.TILES_ENTITIES.register(eventBus);
        MyrcraftFluids.BLOCKS.register(eventBus);
        MyrcraftFluids.ITEMS.register(eventBus);
        MyrcraftFluids.FLUIDS.register(eventBus);
        MyrcraftEntities.ENTITIES.register(eventBus);
    
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    
        eventBus.addListener(this::init);
    
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml"));
    }

    void init(FMLCommonSetupEvent event)
    {
        proxy.registerRenders();
    }
}
