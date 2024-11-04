package net.runelite.client.plugins.microbot.zerozero.varrockcleaner;


import net.runelite.api.ObjectID;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;

import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;

import java.util.concurrent.TimeUnit;

public class VarrockCleanerScript extends Script {

    private enum State {
        TAKE_UNCLEANED,
        CLEAN_FIND,
        STORAGE_CRATE,
        DROP_ITEMS
    }

    private State currentState = State.TAKE_UNCLEANED;

    public boolean run(VarrockCleanerConfig config) {
        shutdown();
        currentState = State.TAKE_UNCLEANED;

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn() || !super.run()) return;

                switch (currentState) {
                    case TAKE_UNCLEANED:
                        takeUncleanedFinds();
                        break;
                    case CLEAN_FIND:
                        cleanFinds();
                        break;
                    case STORAGE_CRATE:
                        storeFindsInCrate();
                        break;
                    case DROP_ITEMS:
                        dropUnwantedItems();
                        break;
                }

            } catch (Exception ex) {
                Microbot.log(ex.getMessage());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);

        return true;
    }

    private void takeUncleanedFinds() {
        if (Rs2Inventory.isFull()) {
            currentState = State.CLEAN_FIND;
            return;
        }
        if (Rs2GameObject.interact(ObjectID.DIG_SITE_SPECIMEN_ROCKS, "Take")) {
            sleepUntil(() -> !Rs2Inventory.isFull(), 5000);
        }
        if (Rs2Inventory.isFull()) {
            currentState = State.CLEAN_FIND;
        }
    }

    private void cleanFinds() {
        if (Rs2Inventory.contains("Uncleaned find") && Rs2GameObject.interact(ObjectID.SPECIMEN_TABLE_24556, "Clean")) {
            sleepUntil(() -> !Rs2Inventory.contains("Uncleaned find"), 30000);
            if (!Rs2Inventory.contains("Uncleaned find")) {
                currentState = State.STORAGE_CRATE;
            }
        }
    }

    private void storeFindsInCrate() {
        if (!Rs2Inventory.contains("Uncleaned find") && Rs2GameObject.interact(ObjectID.STORAGE_CRATE, "Add finds")) {
            Rs2Keyboard.keyPress('2');
            boolean widgetVisible = Rs2Widget.sleepUntilHasWidget("Thanks for helping us out") ||
                    Rs2Widget.isWidgetVisible(15138822, 0);
            if (widgetVisible) {
                currentState = State.DROP_ITEMS;
            }
        }
    }

    private void dropUnwantedItems() {
        String[] itemsToKeep = {"Antique lamp", "Trowel", "Rock pick", "Specimen brush"};
        if (Rs2Inventory.dropAllExcept(itemsToKeep)) {
            currentState = State.TAKE_UNCLEANED;
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
        currentState = State.TAKE_UNCLEANED;
    }
}
