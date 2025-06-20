package ari.classes;

import ari.base.Fish;

import java.util.List;
import java.util.stream.Collectors;

public class FishingOpportunity {

    private final int hour;
    private final String location;
    private final int totalScore;
    private final List<Fish> catchableFish;

    public FishingOpportunity(int hour, String location, int totalScore, List<Fish> catchableFish) {
        this.hour = hour;
        this.location = location;
        this.totalScore = totalScore;
        this.catchableFish = catchableFish;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        String fishNames = catchableFish.stream()
                .map(fish -> String.format("%s (%s)", fish.getName(), fish.getRarity()))
                .collect(Collectors.joining(", "));

        return String.format(
                "🏆 Best Opportunity! 🏆\n" +
                        "   Time: %d:00 (%s)\n" +
                        "   Location: %s\n" +
                        "   Opportunity Score: %d\n" +
                        "   Fish Available: %s",
                hour, (hour < 12 ? "am" : "pm"), location, totalScore, fishNames
        );
    }
}
