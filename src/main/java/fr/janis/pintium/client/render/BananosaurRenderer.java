package fr.janis.pintium.client.render;

import fr.janis.pintium.client.model.BananosaurModel;
import fr.janis.pintium.entities.BananosaurEntity;
import fr.janis.pintium.main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BananosaurRenderer extends MobRenderer<BananosaurEntity, BananosaurModel<BananosaurEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/bananosaur.png");

    public BananosaurRenderer(EntityRendererProvider.Context context) {
        super(context, new BananosaurModel(context.bakeLayer(BananosaurModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(BananosaurEntity entity) {
        return TEXTURE;
    }
}
