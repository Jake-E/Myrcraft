package io.alwa.myrcraft.fluids;

import io.alwa.myrcraft.Myrcraft;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MyrcraftFluids
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Myrcraft.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Myrcraft.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Myrcraft.MOD_ID);
    
    public static RegistryObject<FlowingFluid> latex_fluid = FLUIDS.register("latex_fluid", () -> new LatexFluid.Source(MyrcraftFluids.blue_slime_fluid_properties));
    public static RegistryObject<FlowingFluid> latex_fluid_flowing = FLUIDS.register("latex_fluid_flowing", () -> new LatexFluid.Flowing(MyrcraftFluids.blue_slime_fluid_properties));
    public static RegistryObject<FlowingFluidBlock> latex_fluid_block = BLOCKS.register("latex_fluid_block", () -> new FlowingFluidBlock(latex_fluid, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
    public static RegistryObject<Item> latex_fluid_bucket = ITEMS.register("latex_bucket", () -> new BucketItem(latex_fluid, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(Myrcraft.itemGroup)));
    public static final LatexFluid.Properties blue_slime_fluid_properties = new LatexFluid.Properties(latex_fluid, latex_fluid_flowing, FluidAttributes.builder(new ResourceLocation(Myrcraft.MOD_ID, "block/latex"), new ResourceLocation(Myrcraft.MOD_ID, "block/latex_flowing")).color(0xfffffff).temperature(100)).bucket(latex_fluid_bucket).block(latex_fluid_block);
}
