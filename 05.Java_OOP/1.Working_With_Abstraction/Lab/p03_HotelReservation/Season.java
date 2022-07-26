package _1_Abstraction_and_Enum.Lab.HotelReservation;

public enum Season {
    AUTUMN(1), SPRING(2), WINTER(3), SUMMER(4);

    private int multiplier;

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public static Season parse(String season) {
        return Season.valueOf(season.toUpperCase());
    }

    public int getMultiplier() {
        return multiplier;
    }
}
