package xyz.srgnis.bodyhealthsystem.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;
import xyz.srgnis.bodyhealthsystem.constants.GUIConstants;

import static xyz.srgnis.bodyhealthsystem.util.Draw.drawHealthRectangle;
import static xyz.srgnis.bodyhealthsystem.util.Draw.selectHealthColor;

public class HealScreen extends HandledScreen<HealScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(BHSMain.MOD_ID, "textures/gui/empty.png");

    BodyPartHealButton headButton;
    BodyPartHealButton leftArmButton;
    BodyPartHealButton rightArmButton;
    BodyPartHealButton torsoButton;
    BodyPartHealButton leftLegButton;
    BodyPartHealButton rightLegButton;
    BodyPartHealButton leftFootButton;
    BodyPartHealButton rightFootButton;
    public HealScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super((HealScreenHandler) handler, inventory, title);
        Body body = ((HealScreenHandler) handler).getBody();

        headButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.HEAD),0, 0, GUIConstants.SCALED_HEAD_WIDTH, GUIConstants.SCALED_HEAD_HEIGHT);
        leftArmButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.LEFT_ARM),0, 0, GUIConstants.SCALED_LEFT_ARM_WIDTH, GUIConstants.SCALED_LEFT_ARM_HEIGHT);
        rightArmButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.RIGHT_ARM),0, 0, GUIConstants.SCALED_RIGHT_ARM_WIDTH, GUIConstants.SCALED_RIGHT_ARM_HEIGHT);
        torsoButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.TORSO),0, 0, GUIConstants.SCALED_TORSO_WIDTH, GUIConstants.SCALED_TORSO_HEIGHT);
        leftLegButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.LEFT_LEG),0, 0, GUIConstants.SCALED_LEFT_LEG_WIDTH, GUIConstants.SCALED_LEFT_LEG_HEIGHT);
        rightLegButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.RIGHT_LEG),0, 0, GUIConstants.SCALED_RIGHT_LEG_WIDTH, GUIConstants.SCALED_RIGHT_LEG_HEIGHT);
        leftFootButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.LEFT_FOOT),0, 0, GUIConstants.SCALED_LEFT_FOOT_WIDTH, GUIConstants.SCALED_LEFT_FOOT_HEIGHT);
        rightFootButton = new BodyPartHealButton(body.getPart(PlayerBodyParts.RIGHT_FOOT),0, 0, GUIConstants.SCALED_RIGHT_FOOT_WIDTH, GUIConstants.SCALED_RIGHT_FOOT_HEIGHT);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int startX = (this.width - this.backgroundWidth) / 2;
        int startY = (this.height - this.backgroundHeight) / 2;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        drawTexture(matrices,startX,startY,0,0,this.backgroundWidth,this.backgroundHeight);
    }

    protected void drawButtons(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int startX = (this.width - this.backgroundWidth) / 2;
        startX = ((this.backgroundWidth - GUIConstants.SCALED_BODY_WIDTH) / 2) + startX;
        int startY = (this.height - this.backgroundHeight) / 2;
        startY = startY+((this.backgroundHeight - GUIConstants.SCALED_BODY_HEIGHT) / 2);


        headButton.setX(startX+ GUIConstants.SCALED_HEAD_X_OFFSET);
        headButton.setY(startY+ GUIConstants.SCALED_HEAD_Y_OFFSET);
        headButton.checkAndSetActive();
        this.addDrawableChild(headButton);


        leftArmButton.setX(startX+ GUIConstants.SCALED_LEFT_ARM_X_OFFSET);
        leftArmButton.setY(startY+ GUIConstants.SCALED_LEFT_ARM_Y_OFFSET);
        leftArmButton.checkAndSetActive();
        this.addDrawableChild(leftArmButton);

        rightArmButton.setX(startX+ GUIConstants.SCALED_RIGHT_ARM_X_OFFSET);
        rightArmButton.setY(startY+ GUIConstants.SCALED_RIGHT_ARM_Y_OFFSET);
        rightArmButton.checkAndSetActive();
        this.addDrawableChild(rightArmButton);


        torsoButton.setX(startX+ GUIConstants.SCALED_TORSO_X_OFFSET);
        torsoButton.setY(startY+ GUIConstants.SCALED_TORSO_Y_OFFSET);
        torsoButton.checkAndSetActive();
        this.addDrawableChild(torsoButton);


        leftLegButton.setX(startX+ GUIConstants.SCALED_LEFT_LEG_X_OFFSET);
        leftLegButton.setY(startY+ GUIConstants.SCALED_LEFT_LEG_Y_OFFSET);
        leftLegButton.checkAndSetActive();
        this.addDrawableChild(leftLegButton);

        rightLegButton.setX(startX+ GUIConstants.SCALED_RIGHT_LEG_X_OFFSET);
        rightLegButton.setY(startY+ GUIConstants.SCALED_RIGHT_LEG_Y_OFFSET);
        rightLegButton.checkAndSetActive();
        this.addDrawableChild(rightLegButton);


        leftFootButton.setX(startX+ GUIConstants.SCALED_LEFT_FOOT_X_OFFSET);
        leftFootButton.setY(startY+ GUIConstants.SCALED_LEFT_FOOT_Y_OFFSET);
        leftFootButton.checkAndSetActive();
        this.addDrawableChild(leftFootButton);

        rightFootButton.setX(startX+ GUIConstants.SCALED_RIGHT_FOOT_X_OFFSET);
        rightFootButton.setY(startY+ GUIConstants.SCALED_RIGHT_FOOT_Y_OFFSET);
        rightFootButton.checkAndSetActive();
        this.addDrawableChild(rightFootButton);

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        drawBackground(matrices, delta, mouseX, mouseY);
        super.render(matrices, mouseX, mouseY, delta);
        drawButtons(matrices, delta, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, this.handler.getEntity().getName(), (float)this.titleX, (float)this.titleY, 4210752);
    }

    //TODO refactor BodyPartHealButton into his own class
    class BodyPartHealButton extends ButtonWidget {
        public BodyPart part;
        public BodyPartHealButton(BodyPart part, int x, int y, int width, int height, Text message) {
            super(x, y, width, height, message, button -> {
                ClientNetworking.useHealingItem(handler.getEntity() ,part.getIdentifier(), handler.getItemStack());
                HealScreen.this.close();
            }, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
            this.part = part;
        }
        public BodyPartHealButton(BodyPart part, int x, int y, int width, int height, Text message, PressAction pressAction) {
            super(x, y, width, height, message, pressAction, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
            this.part = part;
        }

        public BodyPartHealButton(BodyPart part, int x, int y, int width, int height) {
            this(part, x,  y,  width, height, Text.literal(""));
        }

        public void checkAndSetActive(){
            active = part.isDamaged();
        }

        @Override
        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta){
            int color = selectHealthColor(part);
            drawHealthRectangle(matrices, x, y, width, height, color);
        }
    }
}
