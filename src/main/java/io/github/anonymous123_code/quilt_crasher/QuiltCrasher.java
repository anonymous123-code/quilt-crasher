package io.github.anonymous123_code.quilt_crasher;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.config.v2.QuiltConfig;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientWorldTickEvents;
import org.quiltmc.qsl.networking.api.client.ClientLoginConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuiltCrasher implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Quilt Crasher");
	public static QuiltCrasherConfig config;
	public static boolean shouldCrash = false;

	@Override
	public void onInitialize(ModContainer mod) {
		if (!QuiltLoader.isDevelopmentEnvironment()) {
			for (int i = 0; i < 10; i++) {
				LOGGER.warn("Quilt crasher present. Instance may crash intentionally. Do not report crashes in this mod set");
			}
		} else {
			LOGGER.warn("Quilt crasher present. Instance may crash intentionally.");
		}
		config = QuiltConfig.create(mod.metadata().id(), "main", QuiltCrasherConfig.class);
		shouldCrash = config.requiredForCrashing.getRealValue().stream().allMatch(QuiltLoader::isModLoaded) && !(config.crashUnless.getRealValue().stream().allMatch(QuiltLoader::isModLoaded) && !config.crashUnless.getRealValue().isEmpty());
        if (shouldCrash)
			switch (config.crashPhase.getRealValue()) {
				case MOD_INIT -> {
					LOGGER.info("Intentionally crashing on mod init");
					throw new RuntimeException(config.crashMessage.getRealValue());
				}
				case WORLD_LOAD -> {
					LOGGER.info("Intentionally crashing on client world load");
					ClientWorldTickEvents.START.register((a, b) -> {
						throw new RuntimeException(config.crashMessage.getRealValue());
					});
				}
				case TITLE_SCREEN -> LOGGER.info("Intentionally crashing on title screen");
				case SERVER_LOAD -> {
					LOGGER.info("Intentionally crashing on server load");
					ClientLoginConnectionEvents.INIT.register((a, b) -> {
						throw new RuntimeException(config.crashMessage.getRealValue());
					});
				}
			}
		else LOGGER.info("No crashing planned :-)");
	}
}

