package net.nimi.snowpunk.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nimi.snowpunk.SnowpunkMod;
import net.nimi.snowpunk.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SnowpunkMod.MOD_ID);

    public static final Supplier<CreativeModeTab> SNOWPUNK_ITEMS_TAB = CREATIVE_MODE_TAB.register("snowpunk_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CONDUCTORS_WRENCH.get()))
                    .title(Component.translatable("creativetab.snowpunk.snowpunk_items"))
                    .displayItems(((parameters, output) -> {
                        output.accept(ModItems.CONDUCTORS_WRENCH);
                        output.accept(ModItems.CHISEL);
                    })).build());

    public static final Supplier<CreativeModeTab> SNOWPUNK_BLOCKS_TAB = CREATIVE_MODE_TAB.register("snowpunk_blocks_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SnowpunkMod.MOD_ID,"snowpunk_items_tab"))
                    .icon(() -> new ItemStack(ModBlocks.BROKEN_RAIL.get()))
                    .title(Component.translatable("creativetab.snowpunk.snowpunk_blocks"))
                    .displayItems(((parameters, output) -> {
                        output.accept(ModBlocks.BROKEN_RAIL);
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
