package fr.janis.pintium.client.model;
// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.janis.pintium.entities.BananoFishEntity;
import fr.janis.pintium.main;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BananoFishModel<T extends BananoFishEntity> extends EntityModel<BananoFishEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(main.MODID, "bananofish"), "main");
    private final ModelPart body;
    private final ModelPart fin_back;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart fin_left;
    private final ModelPart fin_right;
    private final ModelPart tail;
    private final ModelPart body_banana;
    private final ModelPart cou;
    private final ModelPart cou2;
    private final ModelPart cou3;

    public BananoFishModel(ModelPart root) {
        this.body = root.getChild("body");
        this.fin_back = root.getChild("fin_back");
        this.head = root.getChild("head");
        this.nose = root.getChild("nose");
        this.fin_left = root.getChild("fin_left");
        this.fin_right = root.getChild("fin_right");
        this.tail = root.getChild("tail");
        this.body_banana = root.getChild("body_banana");
        this.cou = root.getChild("cou");
        this.cou2 = root.getChild("cou2");
        this.cou3 = root.getChild("cou3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition fin_top = body.addOrReplaceChild("fin_top", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition body_sub_1 = fin_top.addOrReplaceChild("body_sub_1", CubeListBuilder.create().texOffs(20, 0).addBox(-5.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition fin_right2 = body.addOrReplaceChild("fin_right2", CubeListBuilder.create().texOffs(24, 4).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition fin_left2 = body.addOrReplaceChild("fin_left2", CubeListBuilder.create().texOffs(24, 1).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition fin_back = partdefinition.addOrReplaceChild("fin_back", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(11, 0).addBox(-1.0F, -5.0F, -9.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -7.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -3.0F));

        PartDefinition fin_left = partdefinition.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition fin_right = partdefinition.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 1).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 7.0F));

        PartDefinition body_banana = partdefinition.addOrReplaceChild("body_banana", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -5.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 21.5F, -2.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition cou = partdefinition.addOrReplaceChild("cou", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, -4.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 25.0F, -2.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition cou2 = partdefinition.addOrReplaceChild("cou2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 19.5F, -4.0F, -0.6981F, -1.5708F, 0.5236F));

        PartDefinition cou3 = partdefinition.addOrReplaceChild("cou3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 19.0F, -4.5F, -0.1745F, 1.4399F, -0.3054F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body_banana.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        cou.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        cou2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        cou3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(BananoFishEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        float f = 1.0F;
        if (!p_102618_.isInWater()) {
            f = 1.5F;
        }

        this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * p_102621_);
    }
}