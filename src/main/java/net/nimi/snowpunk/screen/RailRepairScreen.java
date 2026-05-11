package net.nimi.snowpunk.screen;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import net.nimi.snowpunk.packet.RepairRailPacket;

public class RailRepairScreen extends AbstractContainerScreen<RailRepairMenu> {
    public RailRepairScreen(RailRepairMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 110;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        addRenderableWidget(Button.builder(Component.literal("Repair"), button -> {
            PacketDistributor.sendToServer(new RepairRailPacket(menu.getRailPos()));
            this.onClose();
        }).bounds(leftPos + 63, topPos + 82, 50, 20).build());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Keret
        guiGraphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF8B7355);
        // Háttér
        guiGraphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF2C2C2C);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawString(this.font, "Rail Repair", leftPos + 10, topPos + 10, 0xFFFFFF);
        guiGraphics.drawString(this.font, "Required materials:", leftPos + 10, topPos + 30, 0xAAAAAA);
        guiGraphics.drawString(this.font, "- 3x Iron Ingot", leftPos + 10, topPos + 45, 0xFFFFFF);
        guiGraphics.drawString(this.font, "- 1x Rail", leftPos + 10, topPos + 57, 0xFFFFFF);

        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}
}
