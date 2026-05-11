package net.nimi.snowpunk.screen;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.nimi.snowpunk.item.ModMenuTypes;

public class RailRepairMenu extends AbstractContainerMenu {
    private final BlockPos railPos;

    // Kliens oldali konstruktor
    public RailRepairMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
        this(containerId, inv, buf.readBlockPos());
    }

    // Szerver oldali konstruktor
    public RailRepairMenu(int containerId, Inventory inv, BlockPos railPos) {
        super(ModMenuTypes.RAIL_REPAIR_MENU.get(), containerId);
        this.railPos = railPos;
    }

    public BlockPos getRailPos() { return railPos; }

    @Override
    public boolean stillValid(Player player) { return true; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
