package net.nimi.snowpunk.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.nimi.snowpunk.SnowpunkMod;
import net.nimi.snowpunk.block.ModBlocks;
import io.netty.buffer.ByteBuf;

public record RepairRailPacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<RepairRailPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SnowpunkMod.MOD_ID, "repair_rail"));

    public static final StreamCodec<ByteBuf, RepairRailPacket> STREAM_CODEC =
            StreamCodec.composite(BlockPos.STREAM_CODEC, RepairRailPacket::pos, RepairRailPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RepairRailPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            Level level = player.level();
            BlockPos pos = packet.pos();

            if (!level.getBlockState(pos).is(ModBlocks.BROKEN_RAIL.get())) return;

            // Megszámolja az itemeket
            int ironCount = 0, railCount = 0;
            for (var stack : player.getInventory().items) {
                if (stack.is(Items.IRON_INGOT)) ironCount += stack.getCount();
                if (stack.is(Items.RAIL)) railCount += stack.getCount();
            }

            if (ironCount >= 3 && railCount >= 1) {
                // Leveszi az itemeket
                int ironLeft = 3, railLeft = 1;
                for (var stack : player.getInventory().items) {
                    if (ironLeft > 0 && stack.is(Items.IRON_INGOT)) {
                        int remove = Math.min(ironLeft, stack.getCount());
                        stack.shrink(remove);
                        ironLeft -= remove;
                    }
                    if (railLeft > 0 && stack.is(Items.RAIL)) {
                        stack.shrink(1);
                        railLeft--;
                    }
                }
                // Kicseréli a blokkot vanilla sínre
                level.setBlock(pos, Blocks.RAIL.defaultBlockState(), 3);
                player.closeContainer();
            }
        });
    }
}
