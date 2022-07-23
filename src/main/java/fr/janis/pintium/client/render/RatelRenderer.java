package fr.janis.pintium.client.render;

import fr.janis.pintium.client.model.RatelModel;
import fr.janis.pintium.entities.RatelEntity;
import fr.janis.pintium.main;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RatelRenderer<Type extends RatelEntity> extends MobRenderer<Type, RatelModel<Type>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/ratel.png");

    public RatelRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new RatelModel<>(p_174304_.bakeLayer(RatelModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RatelEntity entity) {
        return TEXTURE;
    }
}
