package xyz.srgnis.bodyhealthsystem.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;

import java.util.List;
import java.util.Objects;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class GenericScreen extends HandledScreen<ScreenHandler> {
    public static final Text HEAD = Text.literal("HEAD");
    private Body body;

    public GenericScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.body = ((BodyProvider)inventory.player).getBody();
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        fill(matrices, i, j, i+this.backgroundWidth, j+this.backgroundHeight, 0xffffffff);
        this.addDrawableChild(new ButtonWidget(i, j, 50, 20, HEAD, button -> {
            body.healPart(100,body.getPart(PlayerBodyParts.HEAD));
        }));
    }
}
