package fr.janis.pintium.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.LaunchFireBall;
import fr.janis.pintium.network.packet.SpawnABoatPacket;
import fr.janis.pintium.network.packet.SpawnSomeZombiesPacket;
import fr.janis.pintium.network.packet.TransformToABlockPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class SpellsGui extends Screen {
    private final ResourceLocation GUI_TEXTURE_LOCATION = new ResourceLocation(main.MODID, "textures/gui/gui_base.png");
    private final int xSize = 256;
    private final int ySize = 202;
    private final LocalPlayer player;
    private final Level world;

    private int guiLeft;
    private int guiTop;

    public SpellsGui() {
        super(new TranslatableComponent("pintium.guispells.title"));
        this.player = Minecraft.getInstance().player;
        assert Minecraft.getInstance().player != null;
        this.world = Minecraft.getInstance().player.clientLevel;
    }

    //init Gui
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.addWidget(new Button(guiLeft  + (xSize/2) - 70, guiTop + (ySize/2) - 10, 150, 20, new TranslatableComponent("pintium.guispells.button.navis.title"), button -> {
            Network.CHANNEL.sendToServer(new SpawnABoatPacket());
            this.onClose();
        }));
        this.addWidget(new Button(guiLeft + (xSize/2) - 70, guiTop + 5, 150, 20, new TranslatableComponent("pintium.guispells.button.inertium.title"), button -> {
            Network.CHANNEL.sendToServer(new TransformToABlockPacket());
            this.onClose();
        }));
        this.addWidget(new Button(guiLeft + (xSize/2) - 70, guiTop + 123, 150, 20, new TranslatableComponent("pintium.guispells.button.zombium.title"), button -> {
            Network.CHANNEL.sendToServer(new SpawnSomeZombiesPacket());
            this.onClose();
        }));
        this.addWidget(new Button(guiLeft + (xSize/2) - 70, guiTop + 30, 150, 20, new TranslatableComponent("pintium.guispells.button.ignis.title"), button -> {
            Network.CHANNEL.sendToServer(new LaunchFireBall());
            this.onClose();
        }));
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        drawBackGround(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawBackGround(PoseStack matrixStack) {
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindForSetup(GUI_TEXTURE_LOCATION);
        this.blit(matrixStack, guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
