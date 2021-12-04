package com.jab125.thonkutil.gui;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import com.jab125.thonkutil.config.ThonkUtilConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.FullscreenOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.realms.gui.screen.RealmsSettingsScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;

public class ThonkUtilOptionsScreen extends GameOptionsScreen {
    private ButtonListWidget list;
    private final Screen previous;

    public ThonkUtilOptionsScreen(Screen previous) {
        super(previous, MinecraftClient.getInstance().options, new TranslatableText("thonkutil.options"));
        this.previous = previous;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addAll(ThonkUtilConfig.asOptions());
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            ThonkUtilConfigManager.save();
            this.client.setScreen(this.previous);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 5, 0xffffff);
        super.render(matrices, mouseX, mouseY, delta);
        List<OrderedText> tooltip = getHoveredButtonTooltip(this.list, mouseX, mouseY);
        if (tooltip != null) {
            this.renderOrderedTooltip(matrices, tooltip, mouseX, mouseY);
        }

    }

    public void removed() {
        ThonkUtilConfigManager.save();
    }
}
