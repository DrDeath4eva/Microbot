package net.runelite.client.plugins.DrDeath.TunaPotatoeMaker;

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
        name = PluginDescriptor.DrDeath + "Tuna Potato maker",
        description = "Tuna Potato maker",
        tags = {"Tuna Potato maker", "microbot", "DrDeath"},
        enabledByDefault = false
)
@Slf4j
public class TunaPotatoePlugin extends Plugin {
    @Inject
    public TunaPotatoeConfig Tunapotatoeconfig;
    @Provides
    TunaPotatoeConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(TunaPotatoeConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private TunaPotatoeOverlay tunaPotatoeOverlay;

    @Inject
    TunaPotatoeScript tunaPotatoeScript;


    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(tunaPotatoeOverlay);
        }
        tunaPotatoeScript.run(Tunapotatoeconfig);
    }

    protected void shutDown() {
        tunaPotatoeScript.shutdown();
        overlayManager.remove(tunaPotatoeOverlay);
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
