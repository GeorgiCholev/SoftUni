package _1_Abstraction_and_Enum.Lab.HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private DiscountType discountType;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discountType = discountType;
    }

    public String calculatePrice() {
        return String.format("%.2f",
                ((pricePerDay * season.getMultiplier()) * numberOfDays) * discountType.getDiscountMultiplier());
    }
}
