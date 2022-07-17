package fr.janis.pintium.client.render;

import fr.janis.pintium.client.model.TunaModel;
import fr.janis.pintium.entities.TunaEntity;
import fr.janis.pintium.main;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TunaRenderer extends MobRenderer<TunaEntity, TunaModel<TunaEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/tuna.png");

    public TunaRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new TunaModel<>(), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(TunaEntity entity) {
        return TEXTURE;
    }
}
