package io.github.anonymous123_code.quilt_crasher;

import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueList;

public class QuiltCrasherConfig extends ReflectiveConfig {
	public final ModGeneration modGeneration = new ModGeneration();
	public final Crash crash = new Crash();
	public static class ModGeneration extends Section {

		public final TrackedValue<Boolean> showGenerationButton = value(true);
		public final TrackedValue<Integer> modCount = value(10);
	}

	public static class Crash extends Section {
		@Comment("The time the crash should occur.")
		public final TrackedValue<Phase> phase = value(Phase.TITLE_SCREEN);

		@Comment({
			"A list of possible crash requirements if all mod ids in one of the inner lists are present, the game crashes.",
			"Mimics multiple n-way incompatibilities with quilt crasher: When none of the inner list match (or quilt crasher is not present), nothing happens.",
			"If all are present, the game crashes. (Unless unlessRequirements is fulfilled)"
		})
		public final TrackedValue<ValueList<ValueList<String>>> requirements = value(ValueList.create(ValueList.create(""), ValueList.create("", "dummy_mod_0")));

		@Comment({
			"A list of all mods required for the game *not to crash*,",
			"even if all mods provided in requirements are present.",
			"Only applies if not empty."})
		public final TrackedValue<ValueList<String>> unlessRequirements = value(ValueList.create(""));


		@Comment("The message in the exception thrown")
		public final TrackedValue<String> message = value("This is a super important incompatibility /s (Intentionally crashing)");
	}
	public enum Phase {
		MOD_INIT,
		TITLE_SCREEN,
		WORLD_LOAD,
		SERVER_LOAD
	}
}
