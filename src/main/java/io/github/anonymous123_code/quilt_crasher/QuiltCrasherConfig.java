package io.github.anonymous123_code.quilt_crasher;

import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueList;

public class QuiltCrasherConfig extends ReflectiveConfig {
	@Comment("The time the crash should occur.")
	public final TrackedValue<Phase> crashPhase = value(Phase.TITLE_SCREEN);

	@Comment({
		"A list of all mods required for crashing.",
		"Mimics a n-way incompatibility with quilt crasher: When any one of them (including quilt crasher) is not present, nothing happens.",
		"If all are present, the game crashes. (Unless crashUnless is fulfilled)"
	})
	public final TrackedValue<ValueList<String>> requiredForCrashing = value(ValueList.create("", "dummy_mod_0"));

	@Comment({
		"A list of all mods required for the game *not to crash*,",
		"even if all mods provided in requiredForCrashing are present.",
		"Only applies if not empty."})
	public final TrackedValue<ValueList<String>> crashUnless = value(ValueList.create(""));


	@Comment("The message in the exception thrown")
	public final TrackedValue<String> crashMessage = value("This is a super important incompatibility /s (Intentionally crashing)");

	public enum Phase {
		MOD_INIT,
		TITLE_SCREEN,
		WORLD_LOAD,
		SERVER_LOAD
	}
}
