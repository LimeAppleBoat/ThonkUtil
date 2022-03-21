package com.jab125.thonkutil.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public class ThonkUtilOptionsScreen extends GameOptionsScreen {
    private ButtonListWidget list;
    private final Screen previous;
    public static final List<Option> optionList = new ArrayList<>();

    public ThonkUtilOptionsScreen(Screen previous) {
        super(previous, MinecraftClient.getInstance().options, new TranslatableText("thonkutil-trades-v1.options"));
        this.previous = previous;
    }

    protected void init() {

        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addAll(optionList.toArray(new Option[]{}));
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
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

    public void addSingle(Option[] options, ButtonListWidget widget) {
        for (int i = 0; i < options.length; i += 2) {
            widget.addSingleOptionEntry(options[i]);
        }

    }

    //public void removed() {
    //    ThonkUtilTradeConfigManager.save();
    //}

    @Environment(EnvType.CLIENT)
    public static Option createOption(Text text, Screen screen) {
        return new Option("") {
            @Override
            public ClickableWidget createButton(GameOptions options, int x, int y, int width) {
                return new ButtonWidget(x, y, width, 20, text, (buttonWidget) -> {
                    MinecraftClient.getInstance().setScreen(screen);
                });
            }
        };
    }
}
