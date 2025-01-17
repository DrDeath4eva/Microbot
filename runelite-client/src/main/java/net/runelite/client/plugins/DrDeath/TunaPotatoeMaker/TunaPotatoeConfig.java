package net.runelite.client.plugins.DrDeath.TunaPotatoeMaker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("example")
public interface TunaPotatoeConfig extends Config {
    @ConfigSection(
            name = "Como usar",
            description = "",
            position = 0
    )
    String TunaPotatoeConfig = "Como usar";
    @ConfigItem(
            keyName = "about",
            name = "Tuna potatoe",
            position = 0,
            description = "",
            section = TunaPotatoeConfig
    )
    default String about() {
        return "Este plugin hace Tuna Potatoe.\n\nHay que tener los tres ingredientes guardados en el banco\n\nA disfrutar! Viva Venezuela libre!";
    }
}
