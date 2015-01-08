package com.cricketcraft.chisel.client.gui;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.cricketcraft.chisel.block.tileentity.TileEntityAutoChisel;
import com.cricketcraft.chisel.inventory.ContainerAutoChisel;
import com.cricketcraft.chisel.inventory.ContainerAutoChisel.SlotUpgrade;
import com.google.common.collect.Lists;

public class GuiAutoChisel extends GuiContainer {

	private static final ResourceLocation gui = new ResourceLocation("chisel:textures/autochisel-gui.png");

	public GuiAutoChisel(InventoryPlayer inventoryPlayer, TileEntityAutoChisel tileEntityAutoChisel) {
		super(new ContainerAutoChisel(inventoryPlayer, tileEntityAutoChisel));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		for (Slot slot : (List<Slot>) inventorySlots.inventorySlots) {
			if (mouseInside(slot, mouseX - x, mouseY - y)) {
				if (slot instanceof SlotUpgrade) {
					this.func_146283_a(Lists.newArrayList(((SlotUpgrade) slot).upgradeType.getLocalizedName()), mouseX - x, mouseY - y);
				} else if (slot.slotNumber == 1) {
					this.func_146283_a(Lists.newArrayList(StatCollector.translateToLocal("autochisel.slot.target.tooltip")), mouseX - x, mouseY - y);
				}
			}
		}
	}
	
	private boolean mouseInside(Slot slot, int x, int y) {
		return x >= slot.xDisplayPosition && x <= slot.xDisplayPosition + 16 && y >= slot.yDisplayPosition && y <= slot.yDisplayPosition + 16;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(gui);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
