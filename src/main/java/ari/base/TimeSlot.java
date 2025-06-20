package ari.base;

public class TimeSlot {
    private int startHour;
    private int endHour;

    public TimeSlot (int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public boolean isTimeInSlot(int hour) {
        if (startHour <= endHour) {
            return hour >= startHour && hour < endHour;
        } else {
            return hour >= startHour || hour < endHour;
        }
    }

    private String formatHour (int hour) {
        if (hour == 0) return "12am";
        if (hour == 12) return "12pm";
        if (hour < 12) return hour + "am";
        return (hour - 12) + "pm";
    }

    @Override
    public String toString() {
        if (startHour == 0 && endHour == 24) return "All day";
        boolean isSingleHour = (endHour == startHour + 1) || (startHour == 23 && endHour == 0);
        if (isSingleHour) {
            return formatHour(startHour);
        } else return formatHour(startHour) + " - " + formatHour(endHour);
    }
}
