package net.deval.bettermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deval.bettermod.BetterMod;
import net.deval.bettermod.entity.custom.AlphaGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AlphaGolemModel<T extends AlphaGolemEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BetterMod.MOD_ID,"aplhagolem"), "main");
    private final ModelPart body;
    private final ModelPart head;

    public AlphaGolemModel(ModelPart root) {
        this.body = root.getChild("Golem");
        this.head = body.getChild("UperBody").getChild("Head");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Golem = partdefinition.addOrReplaceChild("Golem", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition UperBody = Golem.addOrReplaceChild("UperBody", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -16.0F, -9.0F, 32.0F, 16.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(23, 111).addBox(13.0F, -22.0F, -4.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(30, 127).addBox(13.0F, -22.0F, 3.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(48, 127).addBox(-14.0F, -22.0F, -4.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(39, 127).addBox(-14.0F, -22.0F, 3.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition thing_r1 = UperBody.addOrReplaceChild("thing_r1", CubeListBuilder.create().texOffs(87, 120).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(104, 120).addBox(-19.5F, -2.5F, -1.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, 1.5F, -12.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition backthing_r1 = UperBody.addOrReplaceChild("backthing_r1", CubeListBuilder.create().texOffs(122, 30).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(121, 113).addBox(18.5F, -1.5F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -1.5F, 7.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition Head = UperBody.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(101, 0).addBox(-5.0F, -6.0F, -6.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(121, 105).addBox(-3.0F, 2.0F, -6.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(122, 40).addBox(5.0F, -5.5F, -2.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(122, 40).mirror().addBox(-6.0F, -5.5F, -2.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -9.0F, -9.0F));

        PartDefinition Eye_r1 = Head.addOrReplaceChild("Eye_r1", CubeListBuilder.create().texOffs(101, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -0.25F, -5.25F, 0.0F, 0.0F, 0.7854F));

        PartDefinition horn_r1 = Head.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(122, 40).mirror().addBox(0.0F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(122, 40).addBox(7.0F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.5F, -5.5F, -0.5236F, 0.0F, 0.0F));

        PartDefinition Backpack = UperBody.addOrReplaceChild("Backpack", CubeListBuilder.create().texOffs(0, 35).addBox(-12.0F, -4.0F, 0.0F, 24.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 1.0F));

        PartDefinition Left_Arm = UperBody.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(51, 61).addBox(1.0F, -5.5F, -6.5F, 9.0F, 9.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(81, 35).addBox(0.0F, -5.0F, -6.0F, 8.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(64, 110).addBox(1.0F, 5.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, -12.0F, 0.0F));

        PartDefinition Left_Forearm = Left_Arm.addOrReplaceChild("Left_Forearm", CubeListBuilder.create().texOffs(96, 58).addBox(0.5F, 14.5F, -5.5F, 5.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(101, 15).addBox(0.5F, 11.0F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(88, 105).addBox(0.5F, 7.5F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(125, 89).addBox(4.25F, -4.0F, -1.75F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(125, 79).addBox(4.25F, -4.0F, 2.25F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(51, 84).addBox(-3.0F, 0.0F, -5.0F, 8.0F, 15.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(17, 125).addBox(-2.0F, 15.0F, -4.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(123, 49).addBox(-1.5F, 20.0F, -4.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(122, 123).addBox(-1.5F, 20.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(1, 125).addBox(-1.5F, 20.0F, 2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.0F, 0.0F));

        PartDefinition Right_Arm = UperBody.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(51, 61).mirror().addBox(-10.0F, -5.5F, -6.5F, 9.0F, 9.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(81, 35).mirror().addBox(-8.0F, -5.0F, -6.0F, 8.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 110).mirror().addBox(-6.0F, 5.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-16.0F, -12.0F, 0.0F));

        PartDefinition Right_Forearm = Right_Arm.addOrReplaceChild("Right_Forearm", CubeListBuilder.create().texOffs(96, 58).mirror().addBox(-5.5F, 14.5F, -5.5F, 5.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(101, 15).mirror().addBox(-5.5F, 11.0F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(88, 105).mirror().addBox(-5.5F, 7.5F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(125, 89).mirror().addBox(-5.25F, -4.0F, -1.75F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(125, 79).mirror().addBox(-5.25F, -4.0F, 2.25F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(51, 84).mirror().addBox(-5.0F, 0.0F, -5.0F, 8.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(17, 125).mirror().addBox(-1.0F, 15.0F, -4.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(123, 49).mirror().addBox(-3.5F, 20.0F, -4.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(122, 123).mirror().addBox(-3.5F, 20.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(1, 125).mirror().addBox(-3.5F, 20.0F, 2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 17.0F, 0.0F));

        PartDefinition Torso = Golem.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 61).addBox(-7.0F, -15.0F, -8.0F, 14.0F, 18.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(35, 91).addBox(-0.5F, -12.0F, 3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Left_Leg = Golem.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(129, 55).addBox(4.0F, -6.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(35, 110).addBox(0.0F, -2.0F, -3.0F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 0.0F, -3.0F));

        PartDefinition Lower_Left_Leg = Left_Leg.addOrReplaceChild("Lower_Left_Leg", CubeListBuilder.create().texOffs(57, 129).addBox(-4.0F, -4.0F, -4.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(129, 63).addBox(0.0F, -4.0F, -4.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(88, 84).addBox(-5.0F, 0.0F, -4.0F, 9.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 7.0F, 0.0F));

        PartDefinition Right_Leg = Golem.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(129, 55).mirror().addBox(-5.0F, -6.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(35, 110).mirror().addBox(-7.0F, -2.0F, -3.0F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 0.0F, -3.0F));

        PartDefinition Lower_Right_Leg = Right_Leg.addOrReplaceChild("Lower_Right_Leg", CubeListBuilder.create().texOffs(57, 129).mirror().addBox(1.0F, -4.0F, -4.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(129, 63).mirror().addBox(-3.0F, -4.0F, -4.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(88, 84).mirror().addBox(-4.0F, 0.0F, -4.0F, 9.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 7.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(AlphaGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(AlphaGolemAnimations.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, AlphaGolemAnimations.idle, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }

}
