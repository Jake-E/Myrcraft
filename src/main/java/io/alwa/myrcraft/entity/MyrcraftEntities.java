package io.alwa.myrcraft.entity;

import io.alwa.myrcraft.Myrcraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Resource;

public class MyrcraftEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Myrcraft.MOD_ID);



    public static final RegistryObject<EntityType<BaseAntEntity>> ANT = ENTITIES.register("ant", () -> EntityType.Builder.create(BaseAntEntity::new, EntityClassification.AMBIENT).size(0.5F, 0.9F).build(new ResourceLocation(Myrcraft.MOD_ID, "ant").toString()));

}
