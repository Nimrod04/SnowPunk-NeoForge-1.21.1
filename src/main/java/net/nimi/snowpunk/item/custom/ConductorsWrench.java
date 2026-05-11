package net.nimi.snowpunk.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nimi.snowpunk.block.ModBlocks;
import net.nimi.snowpunk.screen.RailRepairMenu;

public class ConductorsWrench extends Item {
    public ConductorsWrench(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());

        if (state.is(ModBlocks.BROKEN_RAIL.get())) {
            if (!level.isClientSide()) {
                Player player = context.getPlayer();
                BlockPos pos = context.getClickedPos();
                player.openMenu(new SimpleMenuProvider(
                        (id, inv, p) -> new RailRepairMenu(id, inv, pos),
                        Component.literal("Rail Repair")
                ), pos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }
}
