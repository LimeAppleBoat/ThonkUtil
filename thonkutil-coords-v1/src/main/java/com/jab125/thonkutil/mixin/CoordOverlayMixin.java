package com.jab125.thonkutil.mixin;

import com.google.common.base.Strings;
import com.jab125.thonkutil.config.ThonkUtilCoordConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static net.minecraft.client.gui.DrawableHelper.fill;

@Mixin(InGameHud.class)
public abstract class CoordOverlayMixin {


    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(method = "render", at = @At("TAIL"))
    private void injectCoords(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (this.client.options.debugEnabled) return;
        if (!ThonkUtilCoordConfig.ALWAYS_SHOW_COORDS.getValue()) return;
        renderCoords(matrices);
    }

    public void renderCoords(MatrixStack matrices) {
        this.client.getProfiler().push("coords");
        List<String> list = new ArrayList<>();

        //list.add("ThonkUtil " + FabricLoader.getInstance().getModContainer("thonkutil").get().getMetadata().getVersion().getFriendlyString());
        //list.add(this.client.fpsDebugString);

        if (FabricLoader.getInstance().isModLoaded("biomeinfo")) {
            assert MinecraftClient.getInstance().world != null;
            if (MinecraftClient.getInstance().world.isInBuildLimit(MinecraftClient.getInstance().getCameraEntity().getBlockPos()))
                list.add("");
        }
        if (FabricLoader.getInstance().isModLoaded("betterf3")) {
            list.add(String.format(Locale.ROOT, "§cX§aY§bZ§c: %.3f  §a%.5f  §b%.3f", this.client.getCameraEntity().getX(), this.client.getCameraEntity().getY(), this.client.getCameraEntity().getZ()));
        } else {
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.client.getCameraEntity().getX(), this.client.getCameraEntity().getY(), this.client.getCameraEntity().getZ()));
        }
        //list.add(String.format("Render Distance: %s", this.client.options.viewDistance));

        for(int i = 0; i < list.size(); ++i) {
            String string = (String)list.get(i);
            if (!Strings.isNullOrEmpty(string)) {
                Objects.requireNonNull(this.getTextRenderer());
                int j = 9;
                int k = this.getTextRenderer().getWidth(string);
                int m = 2 + j * i;
                fill(matrices, 1, m - 1, 2 + k + 1, m + j - 1, -1873784752);

                if (FabricLoader.getInstance().isModLoaded("betterf3"))
                this.getTextRenderer().drawWithShadow(matrices, string, 2.0F, (float)m, 14737632);
                else
                this.getTextRenderer().draw(matrices, string, 2.0F, (float)m, 14737632);
            }
        }
        this.client.getProfiler().pop();
    }
}