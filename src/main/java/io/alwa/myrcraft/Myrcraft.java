package io.alwa.myrcraft;

import io.alwa.myrcraft.blocks.MyrcraftBlocks;
import io.alwa.myrcraft.blocks.RubberPlanksBlock;
import io.alwa.myrcraft.blocks.RubberSaplingBlock;
import io.alwa.myrcraft.blocks.RubberWoodBlock;
import io.alwa.myrcraft.client.MyrcraftClient;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("myrcraft")
public class Myrcraft {

    private static Logger LOGGER = LogManager.getLogger();
    public final Myrcraft instance;
    public final MyrcraftCommon proxy;
    public final ItemGroup itemGroup;

    public Myrcraft() {
        instance = this;
        proxy = DistExecutor.runForDist(() -> () -> new MyrcraftClient(), () -> () -> new MyrcraftCommon());

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::init);
        eventBus.addGenericListener(Block.class, this::registerBlocks);
        eventBus.addGenericListener(Item.class, this::registerItems);
        eventBus.addGenericListener(TileEntityType.class, this::registerTileEntities);

        itemGroup = new ItemGroup("myrcraft") {
            @Override
            @OnlyIn(Dist.CLIENT)
            public ItemStack createIcon() {
                return new ItemStack(MyrcraftBlocks.RUBBERWOOD);
            }
        };

    }

    private void init(FMLCommonSetupEvent event) {

    }

    private void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        //Register here

        registry.register(new RubberWoodBlock().setRegistryName("rubberwood"));
        registry.register(new RubberPlanksBlock().setRegistryName("rubberplanks"));
        registry.register(new RubberSaplingBlock().setRegistryName("rubbersapling"));

    }

    private void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new BlockItem(MyrcraftBlocks.RUBBERWOOD, new Item.Properties().group(itemGroup)).setRegistryName("rubberwood"));
        registry.register(new BlockItem(MyrcraftBlocks.RUBBERPLANKS, new Item.Properties().group(itemGroup)).setRegistryName("rubberplanks"));
        registry.register(new BlockItem(MyrcraftBlocks.RUBBERSAPLING, new Item.Properties().group(itemGroup)).setRegistryName("rubbersapling"));
    }

    private void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {

    }

}
