package fr.janis.pintium.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import sun.nio.ch.Net;

import java.net.IDN;
import java.util.Objects;
import java.util.function.Predicate;

public class TPObjectGUI extends Screen {
    private final ResourceLocation GUI_TEXTURE_LOCATION = new ResourceLocation(main.MODID, "textures/gui/gui_base.png");
    private final int xSize = 256;
    private final int ySize = 202;
    private final ClientPlayerEntity player;
    private final World world;

    private int guiLeft;
    private int guiTop;

    private TextFieldWidget xbox;
    private TextFieldWidget ybox;
    private TextFieldWidget zbox;

    private final TranslationTextComponent xbox_label = new TranslationTextComponent("pintium.tpobjectgui.field.x.title");
    private final TranslationTextComponent ybox_label = new TranslationTextComponent("pintium.tpobjectgui.field.y.title");
    private final TranslationTextComponent zbox_label = new TranslationTextComponent("pintium.tpobjectgui.field.z.title");

    private String x;
    private String y;
    private String z;

    private final Predicate<String> filter = (p_210141_0_) -> {
        if (StringUtils.isNullOrEmpty(p_210141_0_)) {
            return true;
        } else {
            String[] astring = p_210141_0_.split(":");
            if (astring.length == 0) {
                return true;
            } else {
                try {
                    String s = IDN.toASCII(astring[0]);
                    return true;
                } catch (IllegalArgumentException illegalargumentexception) {
                    return false;
                }
            }
        }
    };

    public TPObjectGUI() {
        super(new TranslationTextComponent("pintium.TPObjectGUI.title"));
        this.player = Minecraft.getInstance().player;
        assert Minecraft.getInstance().player != null;
        this.world = Minecraft.getInstance().player.level;
    }

    public void tick() {
        this.xbox.tick();
    }

    //init Gui
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        xbox = new TextFieldWidget(Minecraft.getInstance().font, guiLeft  + (xSize/2) - 70, guiTop + (ySize/2) - 90 + 20, 150, 20, xbox_label);
        ybox = new TextFieldWidget(Minecraft.getInstance().font, guiLeft  + (xSize/2) - 70, guiTop + (ySize/2) - 35 + 20, 150, 20, ybox_label);
        zbox = new TextFieldWidget(Minecraft.getInstance().font, guiLeft  + (xSize/2) - 70, guiTop + (ySize/2) + 20 + 20, 150, 20, zbox_label);
        this.xbox.setMaxLength(128);
        this.ybox.setMaxLength(128);
        this.zbox.setMaxLength(128);
        this.xbox.setValue(this.x);
        this.ybox.setValue(this.y);
        this.zbox.setValue(this.z);
        this.xbox.setFilter(this.filter);
        this.ybox.setFilter(this.filter);
        this.zbox.setFilter(this.filter);
        this.children.add(this.xbox);
        this.children.add(this.ybox);
        this.children.add(this.zbox);
        this.addButton(new Button(this.width / 2 - 100, guiTop + (ySize/2) + 20 + 20 + 30, 200, 20, new TranslationTextComponent("addServer.add"), (p_213030_1_) -> {
            this.onAdd();
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        drawBackGround(matrixStack);
        drawString(matrixStack, this.font, xbox_label, this.width / 2 - 100, guiTop + (ySize/2) - 90 + 20 - 15, 10526880);
        drawString(matrixStack, this.font, ybox_label, this.width / 2 - 100, guiTop + (ySize/2) - 35 + 20 - 15, 10526880);
        drawString(matrixStack, this.font, zbox_label, this.width / 2 - 100, guiTop + (ySize/2) + 20 + 20 - 15, 10526880);
        this.xbox.render(matrixStack, mouseX, mouseY, partialTicks);
        this.ybox.render(matrixStack, mouseX, mouseY, partialTicks);
        this.zbox.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawBackGround(MatrixStack matrixStack) {
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(GUI_TEXTURE_LOCATION);
        this.blit(matrixStack, guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void resize(Minecraft p_231152_1_, int p_231152_2_, int p_231152_3_) {
        String s = this.xbox.getValue();
        String st = this.ybox.getValue();
        String str = this.zbox.getValue();
        this.init(p_231152_1_, p_231152_2_, p_231152_3_);
        this.xbox.setValue(s);
        this.ybox.setValue(st);
        this.zbox.setValue(str);
    }

    private void onAdd() {
        this.x = this.xbox.getValue();
        this.y = this.ybox.getValue();
        this.z = this.zbox.getValue();

        try {
            double tx = Double.parseDouble(x);
            double ty = Double.parseDouble(y);
            double tz = Double.parseDouble(z);
            if (tx > 0){
                tx+=0.5;
            }
            else{
                tx-=0.5;
            }
            if (tz > 0){
                tz+=0.5;
            }
            else {
                tz-=0.5;
            }

            Network.CHANNEL.sendToServer(new TPlayerPacket(tx,ty,tz));
        }
        catch (Exception e) {
            player.displayClientMessage(ITextComponent.nullToEmpty(new TranslationTextComponent("pintium.TPObjectGUI.error").getString()), true);
        }
        this.onClose();
    }
}