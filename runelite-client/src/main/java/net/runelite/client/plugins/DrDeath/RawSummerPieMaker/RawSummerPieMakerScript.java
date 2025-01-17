package net.runelite.client.plugins.DrDeath.RawSummerPieMaker;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.antiban.Rs2Antiban;
import net.runelite.client.plugins.microbot.util.antiban.Rs2AntibanSettings;
import net.runelite.client.plugins.microbot.util.bank.Rs2Bank;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class RawSummerPieMakerScript extends Script {

    public static boolean test = false;
    public boolean run(RawSummerPieMakerConfig config) {


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
                while (Rs2Bank.hasItem("Pie shell") && (Rs2Bank.hasItem("Strawberry"))) {
                    Rs2Bank.withdrawX("Pie shell", 14);
                    Rs2Bank.withdrawX("Strawberry", 14);
                    sleepUntil(() -> Rs2Inventory.hasItem("Pie shell") || Rs2Inventory.hasItem("Strawberry"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Pie shell", "Strawberry");
                    sleep(500,1200);
                    Rs2Keyboard.keyPress(KeyEvent.VK_SPACE);
                    sleep(15000,18000);
                    Rs2Bank.walkToBankAndUseBank();
                    Rs2Bank.depositAll();
                } while (Rs2Bank.hasItem("Part summer pie (strawberry)") && Rs2Bank.hasItem("Watermelon")) {
                    Rs2Bank.withdrawX("Part summer pie (strawberry)", 14);
                    Rs2Bank.withdrawX("Watermelon", 14);
                    sleepUntil(() -> Rs2Inventory.hasItem("Part summer pie (strawberry)") || Rs2Inventory.hasItem("Watermelon"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Part summer pie (strawberry)", "Watermelon");
                    sleep(1000, 3000);
                    Rs2Keyboard.keyPress(KeyEvent.VK_SPACE);
                    sleep(15000, 18000);
                    Rs2Bank.walkToBankAndUseBank();
                    Rs2Bank.depositAll();
                }
                while (Rs2Bank.hasItem("Part summer pie (watermelon)") && Rs2Bank.hasItem("Cooking apple")) {
                    Rs2Bank.withdrawX("Part summer pie (watermelon)", 14);
                    Rs2Bank.withdrawX("Cooking apple", 14);
                    sleepUntil(() -> Rs2Inventory.hasItem("Part summer pie (watermelon)") || Rs2Inventory.hasItem("Cooking apple"));
                    Rs2Bank.closeBank();
                    Rs2Inventory.combine("Part summer pie (watermelon)", "Cooking apple");
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