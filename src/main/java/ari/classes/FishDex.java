package ari.classes;

import ari.base.Fish;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

public class FishDex {
    private Map<String, Fish> fishMap = new HashMap<>();

    public void loadFishFromResources() {
        Gson gson = new Gson();
        String fileName = "fish_data.json";

        InputStream inputStream = FishDex.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            System.err.println("Could not find resource file. " + fileName);
            return;
        }

        try (Reader reader = new InputStreamReader(inputStream)) {
            Type fishListType = new TypeToken<List<Fish>>(){}.getType();
            List<Fish> fishList = gson.fromJson(reader, fishListType);

            for (Fish fish : fishList) {
                fishMap.put(fish.getName(), fish);
            }
            System.out.println("Successfully loaded " + fishMap.size() + " fish from resources.");
        } catch (Exception e) {
            System.err.println("Error reading or parsing the fish data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Fish getFishByName(String name) {
        return fishMap.get(name);
    }

    public List<Fish> findMissingCatchableFish(PlayerLog playerLog, int currentHour, String location) {
        List<Fish> missingFish = new ArrayList<>();

        for (Fish fish : this.fishMap.values()) {
            if (fish.getLocation().equalsIgnoreCase(location) &&
            fish.isCatchableAt(currentHour) &&
            !playerLog.hasCaught(fish.getName())) {
                missingFish.add(fish);
            }
        }
        return missingFish;
    }

    public List<Fish> findGloballyMissingAndCatchable(PlayerLog playerLog, int currentHour) {
        List<Fish> availableNow = new ArrayList<>();

        for (Fish fish : this.fishMap.values()) {
            if (fish.isCatchableAt(currentHour) && !playerLog.hasCaught(fish.getName())) {
                availableNow.add(fish);
            }
        }

        availableNow.sort((fish1, fish2) -> fish2.getRarity().compareTo(fish1.getRarity()));
        return availableNow;
    }

    public Optional<FishingOpportunity> findBestFishingOpportunity (PlayerLog playerLog) {
        List<FishingOpportunity> allOpportunity = new ArrayList<>();
        List<String> locations = List.of("Pier", "Workshop", "Flower Shop");

        for (String location : locations) {
            for (int hour = 0; hour < 24; hour++) {
                List<Fish> availableFish = findMissingCatchableFish(playerLog, hour, location);

                if (!availableFish.isEmpty()) {
                    int currentScore = availableFish.stream()
                            .mapToInt(fish -> fish.getRarity().getScoreValue())
                            .sum();

                    allOpportunity.add(new FishingOpportunity(hour, location, currentScore, availableFish));
                }
            }
        }

        return allOpportunity.stream()
                .max(Comparator.comparingInt(FishingOpportunity::getTotalScore));
    }
}
