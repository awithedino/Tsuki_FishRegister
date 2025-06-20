package ari.base;

import ari.enume.Rarity;
import java.util.List;
import java.util.stream.Collectors;

public class Fish {
    private String name;
    private Rarity rarity;
    private String location;
    private List<TimeSlot> catchWindows;

    public Fish(String name, Rarity rarity, String location, List<TimeSlot> catchWindows) {
        this.name = name;
        this.rarity = rarity;
        this.location = location;
        this.catchWindows = catchWindows;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getLocation() {
        return location;
    }

    public List<TimeSlot> getCatchWindows() {
        return catchWindows;
    }

    public boolean isCatchableAt(int hour) {
        for (TimeSlot slot : catchWindows) {
            if (slot.isTimeInSlot(hour)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String timeString = catchWindows.stream()
                .map(TimeSlot::toString)
                .collect(Collectors.joining(", "));

        return "--- " + name + " ---\n" +
                "  Rarity: " + rarity + "\n" +
                "  Price: " + rarity.getPrice() + " Carrots\n" +
                "  Location: " + location + "\n" +
                "  Catchable at: " + timeString;
    }
}
