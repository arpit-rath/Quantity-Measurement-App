public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /*
     * Convert value in this unit → base unit (feet)
     */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /*
     * Convert value from base unit (feet) → this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}