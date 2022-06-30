package _1_Abstraction_and_Enum.Lab.HotelReservation;

public enum DiscountType {

    VIP(0.80), SECOND_VISIT(0.90), NONE(1);

    private double discountMultiplier;

    DiscountType(double discountMultiplier) {
        this.discountMultiplier = discountMultiplier;
    }

    public double getDiscountMultiplier() {
        return discountMultiplier;
    }

    public static DiscountType parse(String discountType) {
        switch (discountType) {
            case "VIP":
                return DiscountType.VIP;
            case "SecondVisit":
                return DiscountType.SECOND_VISIT;
            case "None":
                return DiscountType.NONE;
            default:
                throw new IllegalArgumentException("Invalid Input" + discountType);
        }
    }
}
