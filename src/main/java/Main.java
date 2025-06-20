import ari.base.Fish;

import ari.classes.*;
import java.util.List;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // --- SETUP ---
        FishDex dex = new FishDex();
        dex.loadFishFromResources();
        PlayerLog myLog = new PlayerLog();

        // Simulate a player who has caught the common fish, but is missing rarer ones.
        myLog.addCaughtFish(dex.getFishByName("Rusty Anchovy"));
        myLog.addCaughtFish(dex.getFishByName("Mudskipper"));

        // --- THE BIG QUESTION ---
        System.out.println("\n--- Planning a Fishing Trip ---");
        System.out.println("Finding the absolute best time and place to fish...");

        // --- CALL THE NEW METHOD ---
        Optional<FishingOpportunity> bestTime = dex.findBestFishingOpportunity(myLog);

        bestTime.ifPresentOrElse(
                opportunity -> System.out.println("\n" + opportunity), // This runs if an opportunity was found
                () -> System.out.println("\n Congratulations! You've caught every fish!") // This runs if it was empty
        );
    }
}