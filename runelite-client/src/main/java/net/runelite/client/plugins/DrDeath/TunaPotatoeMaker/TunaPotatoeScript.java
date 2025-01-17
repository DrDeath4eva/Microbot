package net.runelite.client.plugins.DrDeath.TunaPotatoeMaker;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.antiban.Rs2Antiban;
import net.runelite.client.plugins.microbot.util.antiban.Rs2AntibanSettings;
import net.runelite.client.plugins.microbot.util.bank.Rs2Bank;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class TunaPotatoeScript extends Script {

    public static boolean test = false;
    public boolean run(TunaPotatoeConfig config) {


        Microbot.enableAutoRunOn = false;

        Rs2Antiban.resetAntibanSettings();
        Rs2Antiban.antibanSetupTemplates.applySmithingSetup();
        Rs2AntibanSettings.dynamicActivity = true;
        Rs2AntibanSettings.dynamicIntensity = true;
        Rs2AntibanSettings.actionCooldownChance = 0.1;
        Rs2AntibanSettings.microBreakChance = 0.1;
        Rs2AntibanSettings.microBreakDurationLow = 0;
        Rs2AntibanSettings.microBreakDurationHigh = 0;

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();


                Rs2Bank.walkToBankAndUseBank();
                Rs2Bank.openBank();
                sleepUntil(() -> Rs2Bank.isOpen(), 5000);
                while (Rs2Bank.hasItem("Bowl") && (Rs2Bank.hasItem("Tuna"))) {
                    Rs2Bank.withdrawX("Bowl", 13);
                    Rs2Bank.withdrawX("Tuna", 13);
                    Rs2Bank.withdrawOne("knife");
                    sleepUntil(() -> Rs2Inventory.hasItem("Bowl") || Rs2Inventory.hasItem("Tuna"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Bowl", "Tuna");
                    sleep(500,1200);
                    Rs2Keyboard.keyPress(KeyEvent.VK_SPACE);
                    sleep(15000,18000);
                    Rs2Bank.walkToBankAndUseBank();
                    Rs2Bank.depositAll();
                } while (Rs2Bank.hasItem("Chopped tuna") && Rs2Bank.hasItem("Sweetcorn")) {
                    Rs2Bank.withdrawX("Chopped tuna", 14);
                    Rs2Bank.withdrawX("Sweetcorn", 14);
                    sleepUntil(() -> Rs2Inventory.hasItem("Chopped tuna") || Rs2Inventory.hasItem("Sweetcorn") || Rs2Inventory.hasItem("knife"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Chopped tuna", "Tuna");
                    sleep(1000, 3000);
                    Rs2Keyboard.keyPress(KeyEvent.VK_SPACE);
                    sleep(15000, 18000);
                    Rs2Bank.walkToBankAndUseBank();
                    Rs2Bank.depositAll();
                }
                while (Rs2Bank.hasItem("tuna and corn") && Rs2Bank.hasItem("Potato with butter")) {
                    Rs2Bank.withdrawX("Tuna and corn", 14);
                    Rs2Bank.withdrawX("Cooking apple", 14);
                    sleepUntil(() -> Rs2Inventory.hasItem("Tuna and corn") || Rs2Inventory.hasItem("Potato with butter"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Tuna and corn", "Potato with butter");
                    sleep(1000, 3000);
                    Rs2Keyboard.keyPress(KeyEvent.VK_SPACE);
                    sleep(15000, 18000);
                    Rs2Bank.walkToBankAndUseBank();
                    Rs2Bank.depositAll();

                }
                    long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Total time for loop " + totalTime);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}