package net.nimi.snowpunk;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.nimi.snowpunk.item.ModMenuTypes;
import net.nimi.snowpunk.packet.RepairRailPacket;
import net.nimi.snowpunk.screen.RailRepairScreen;

@EventBusSubscriber(modid = SnowpunkMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.RAIL_REPAIR_MENU.get(), RailRepairScreen::new);
    }
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(
                RepairRailPacket.TYPE,
                RepairRailPacket.STREAM_CODEC,
                RepairRailPacket::handle
        );
    }
}
