package net.runelite.client.plugins.DrDeath.RawSummerPieMaker;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.DrDeath + "Raw Summerpie Maker",
        description = "Raw Summerpie Maker",
        tags = {"Raw Summerpie Maker", "microbot", "DrDeath"},
        enabledByDefault = false
)
@Slf4j
public class RawSummerPieMakerPlugin extends Plugin {
    @Inject
    private RawSummerPieMakerConfig RawSummerPieConfig;
    @Provides
    RawSummerPieMakerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RawSummerPieMakerConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private RawSummerPieMakerOverlay rawSummerPieMakerOverlay;

    @Inject
    RawSummerPieMakerScript rawSummerPieMakerScript;


    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(rawSummerPieMakerOverlay);
        }
        rawSummerPieMakerScript.run(RawSummerPieConfig);
    }

    protected void shutDown() {
        rawSummerPieMakerScript.shutdown();
        overlayManager.remove(rawSummerPieMakerOverlay);
    }
    int ticks = 10;
    @Subscribe
    public void onGameTick(GameTick tick)
    {
        //System.out.println(getName().chars().mapToObj(i -> (char)(i + 3)).map(String::valueOf).collect(Collectors.joining()));

        if (ticks > 0) {
            ticks--;
        } else {
            ticks = 10;
        }

    }

}
