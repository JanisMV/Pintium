package fr.janis.pintium.client.render;

import com.ibm.icu.text.Normalizer2;
import fr.janis.pintium.client.model.TunaModel;
import fr.janis.pintium.entities.TunaEntity;
import fr.janis.pintium.main;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TunaRenderer extends MobRenderer<TunaEntity, TunaModel<TunaEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/entity/tuna.png");

    public TunaRenderer(EntityRendererProvider.Context context) {
        super(context, new TunaModel(context.bakeLayer(TunaModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(TunaEntity entity) {
        return TEXTURE;
    }
}
