package fr.janis.pintium.client.model;// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.janis.pintium.entities.TunaEntity;
import fr.janis.pintium.main;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class TunaModel<T extends TunaEntity> extends EntityModel<TunaEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(main.MODID, "tuna"), "main");
    private final ModelPart body_front;
    private final ModelPart body_back;
    private final ModelPart head;
    private final ModelPart fin_back_1;
    private final ModelPart fin_back_2;
    private final ModelPart fin_left;
    private final ModelPart fin_right;

    public TunaModel(ModelPart root) {
        this.body_front = root.getChild("body_front");
        this.body_back = root.getChild("body_back");
        this.head = root.getChild("head");
        this.fin_back_1 = root.getChild("fin_back_1");
        this.fin_back_2 = root.getChild("fin_back_2");
        this.fin_left = root.getChild("fin_left");
        this.fin_right = root.getChild("fin_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body_front = partdefinition.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -4.0F));

        PartDefinition bone = body_front.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.5F, 0.0F, -1.5708F, 0.0F, -0.7854F));

        PartDefinition bone2 = body_front.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 1.5F, 0.0F, -1.5708F, 0.0F, 0.7854F));

        PartDefinition body_back = partdefinition.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 4.0F));

        PartDefinition dick = body_back.addOrReplaceChild("dick", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, -4.0F, 6.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -4.0F));

        PartDefinition tail = body_back.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -4.0F));

        PartDefinition fin_back_1 = partdefinition.addOrReplaceChild("fin_back_1", CubeListBuilder.create().texOffs(4, 2).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.5F, 1.0F));

        PartDefinition fin_back_2 = partdefinition.addOrReplaceChild("fin_back_2", CubeListBuilder.create().texOffs(2, 3).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.5F, 3.0F));

        PartDefinition fin_left = partdefinition.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition fin_right = partdefinition.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body_front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(TunaEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        float f = 1.0F;
        float f1 = 1.0F;
        if (!p_102618_.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }

        this.body_back.yRot = -f * 0.25F * Mth.sin(f1 * 0.6F * p_102621_);

    }
}