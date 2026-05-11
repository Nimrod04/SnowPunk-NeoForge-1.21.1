package net.nimi.snowpunk.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nimi.snowpunk.SnowpunkMod;
import net.nimi.snowpunk.screen.RailRepairMenu;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, SnowpunkMod.MOD_ID);

    public static final Supplier<MenuType<RailRepairMenu>> RAIL_REPAIR_MENU =
            MENUS.register("rail_repair_menu",
                    () -> IMenuTypeExtension.create(RailRepairMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
