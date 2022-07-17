package fr.janis.pintium.client.render;

import fr.janis.pintium.client.model.BananoFishModel;
import fr.janis.pintium.entities.BananoFishEntity;
import fr.janis.pintium.main;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BananoFishRenderer extends MobRenderer<BananoFishEntity, BananoFishModel<BananoFishEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/bananofish.png");

    public BananoFishRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new BananoFishModel<>(), 0.4F);
    }

    @Override //getEntityTexture - getTextureLocation
    public ResourceLocation getTextureLocation(BananoFishEntity entity) {
        return TEXTURE;
    }
}
