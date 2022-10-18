package xyz.srgnis.bodyhealthsystem.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;

public class GenericScreen extends HandledScreen<ScreenHandler> {
    public static final Text HEAD = Text.literal("HEAD");
    public static final Text LEFT_ARM = Text.literal("LEFT ARM");
    public static final Text RIGHT_ARM = Text.literal("RIGHT ARM");
    public static final Text TORSO = Text.literal("TORSO");
    public static final Text LEFT_LEG = Text.literal("LEFT LEG");
    public static final Text RIGHT_LEG = Text.literal("RIGHT LEG");
    public static final Text LEFT_FOOT = Text.literal("LEFT FOOT");
    public static final Text RIGHT_FOOT = Text.literal("RIGHT FOOT");

    private Body body;
    private int buttonWidth = 75;
    private int buttonHeight = 20;
    private int buttonMargin = 2;
    private int total = (buttonHeight+buttonMargin)*5;

    public GenericScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.body = ((BodyProvider)inventory.player).getBody();
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int row = 0;
        int startX = (this.width - this.backgroundWidth) / 2;
        int startY = (this.height - this.backgroundHeight) / 2;
        int buttonStartY = startY+((this.backgroundHeight - total) / 2);
        int centerX = ((this.backgroundWidth - buttonWidth) / 2) + startX;
        int centerLeftX = ((this.backgroundWidth - buttonWidth * 2) / 2) + startX;

        fill(matrices, startX, startY, startX+this.backgroundWidth, startY+this.backgroundHeight, 0xffffffff);

        this.addDrawableChild(new ButtonWidget(centerX, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, HEAD, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.HEAD));
        }));
        row++;
        this.addDrawableChild(new ButtonWidget(centerLeftX, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, LEFT_ARM, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.LEFT_ARM));
        }));

        this.addDrawableChild(new ButtonWidget(centerLeftX+buttonMargin+buttonWidth, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, RIGHT_ARM, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.RIGHT_ARM));
        }));
        row++;
        this.addDrawableChild(new ButtonWidget(centerX, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, RIGHT_ARM, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.TORSO));
        }));
        row++;
        this.addDrawableChild(new ButtonWidget(centerLeftX, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, LEFT_LEG, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.LEFT_LEG));
        }));
        this.addDrawableChild(new ButtonWidget(centerLeftX+buttonMargin+buttonWidth, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, RIGHT_LEG, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.RIGHT_LEG));
        }));
        row++;
        this.addDrawableChild(new ButtonWidget(centerLeftX, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, LEFT_FOOT, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.LEFT_FOOT));
        }));
        this.addDrawableChild(new ButtonWidget(centerLeftX+buttonMargin+buttonWidth, buttonStartY+((buttonHeight+buttonMargin)*row), buttonWidth, buttonHeight, RIGHT_FOOT, button -> {
            body.healPart(4,body.getPart(PlayerBodyParts.RIGHT_FOOT));
        }));
    }
}
