package ari.classes;

import ari.base.Fish;

import java.util.HashSet;
import java.util.Set;

public class PlayerLog {
    private Set<String> caughtFishNames = new HashSet<>();

    public void addCaughtFish(Fish fish) {
        if (caughtFishNames.add(fish.getName())) {
            System.out.println("Log: You've caught a " + fish.getName() + " for the first time!");
        } else {
            System.out.println("You caught another " + fish.getName() + ".");
        }
    }

    public boolean hasCaught(String fishName) {
        return caughtFishNames.contains(fishName);
    }

    public void printLog() {
        System.out.println("\n--- Your Catch Log ---");
        if (caughtFishNames.isEmpty()) {
            System.out.println("You haven't logged any fish yet.");
        } else {
            caughtFishNames.forEach(System.out::println);
        }
        System.out.println("----------------");
    }
}
