package ari.enume;

public enum Rarity {
    COMMON("Common", 20, 1),
    UNCOMMON("Uncommon", 50, 3),
    RARE("Rare", 120, 10),
    LEGENDARY("Legendary", 300, 50);

    private final String displayName;
    private final int price;
    private final int scoreValue;

    Rarity(String displayName, int price, int scoreValue) {
        this.displayName = displayName;
        this.price = price;
        this.scoreValue = scoreValue;
    }

    public int getPrice() {
        return price;
    }
    public int getScoreValue() {
        return scoreValue;
    }

    public String toString() {
        return displayName;
    }
}
