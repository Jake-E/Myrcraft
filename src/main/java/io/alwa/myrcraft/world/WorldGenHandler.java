package io.alwa.myrcraft.world;

import io.alwa.myrcraft.MyrcraftConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenHandler {

    public static final WorldGenRubberTree TREE = new WorldGenRubberTree(false);

    @SubscribeEvent
    public static void decorateTrees(DecorateBiomeEvent.Decorate event) {
        if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE && MyrcraftConfig.rubbertree.tree_chance > 0 && event.getRand().nextInt(MyrcraftConfig.rubbertree.tree_chance) == 0) {
            int x = event.getRand().nextInt(16) + 8;
            int z = event.getRand().nextInt(16) + 8;

            BlockPos pos = event.getWorld().getHeight(event.getPos().add(x, 0, z));

            if (event.getWorld().getBiome(pos).getTemperature(pos) >= 0.95F) {
                TREE.setDecorationDefaults();
                TREE.generate(event.getWorld(), event.getRand(), pos);
            }
        }
    }
}
