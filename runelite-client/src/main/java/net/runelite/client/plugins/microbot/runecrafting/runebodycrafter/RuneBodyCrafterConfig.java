package net.runelite.client.plugins.microbot.runecrafting.runebodycrafter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("RuneBodyCrafter")
public interface RuneBodyCrafterConfig extends Config {

    @ConfigSection(
            name = "Instructions",
            description = "",
            position = 0
    )
    String instructionsSection = "Instructions";

    @ConfigItem(
            keyName = "instructions",
            name = "",
            position = 0,
            description = "",
            section = instructionsSection
    )
    default String about() {
        return "This plugin crafts runes at the body altar.\n\nOnly works with Pure essence.\n\nBe near the Edgeville bank before starting the script and have a Body Tiara in the Bank.\n\nThank you to George and Harvest101 for the help!";
    }
}
