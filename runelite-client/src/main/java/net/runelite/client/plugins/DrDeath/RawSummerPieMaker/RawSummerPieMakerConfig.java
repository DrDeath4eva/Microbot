package net.runelite.client.plugins.DrDeath.RawSummerPieMaker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("example")
public interface RawSummerPieMakerConfig extends Config {
    @ConfigSection(
            name = "Como usar",
            description = "",
            position = 0
    )
    String SummerPieMaker = "Como usar";
    @ConfigItem(
            keyName = "about",
            name = "",
            position = 0,
            description = "",
            section = SummerPieMaker
    )
    default String about() {
        return "Este plugin hace raw summerpies.\n\nHay que tener los tres ingredientes guardados en el banco\n\nA disfrutar! Viva Venezuela libre!";
    }
}
