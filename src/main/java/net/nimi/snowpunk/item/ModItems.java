package net.nimi.snowpunk.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nimi.snowpunk.SnowpunkMod;
import net.nimi.snowpunk.item.custom.ChiselItem;
import net.nimi.snowpunk.item.custom.ConductorsWrench;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SnowpunkMod.MOD_ID);

    public static final DeferredItem<Item> CONDUCTORS_WRENCH = ITEMS.register("conductors_wrench",
            () -> new ConductorsWrench(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
