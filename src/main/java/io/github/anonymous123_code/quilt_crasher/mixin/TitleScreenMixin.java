package io.github.anonymous123_code.quilt_crasher.mixin;

import io.github.anonymous123_code.quilt_crasher.QuiltCrasher;
import io.github.anonymous123_code.quilt_crasher.QuiltCrasherConfig;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.anonymous123_code.quilt_crasher.QuiltCrasher.config;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(method = "render", at = @At("TAIL"))
	private void onTitleScreenShow(CallbackInfo ci) {
		if (QuiltCrasher.shouldCrash && config.crashPhase.getRealValue() == QuiltCrasherConfig.Phase.TITLE_SCREEN) {
			throw new RuntimeException(config.crashMessage.getRealValue());
		}
	}
}
