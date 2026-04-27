public class QuantityMeasurementApp {

    /*
     * ===========================
     * ENUM: LengthUnit
     * Base unit = FEET
     * ===========================
     */
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    /*
     * ===========================
     * CLASS: QuantityLength
     * ===========================
     */
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        /*
         * Instance conversion method (returns NEW object)
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = toBaseUnit();
            double converted = targetUnit.fromFeet(base);

            return new QuantityLength(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    /*
     * ===========================
     * STATIC CONVERSION API
     * ===========================
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double base = source.toFeet(value);
        return target.fromFeet(base);
    }

    /*
     * ===========================
     * METHOD OVERLOADING DEMO
     * ===========================
     */

    // Method 1
    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        return convert(value, from, to);
    }

    // Method 2
    public static double demonstrateLengthConversion(QuantityLength q,
                                                     LengthUnit to) {
        return q.convertTo(to).getValue();
    }

    /*
     * ===========================
     * MAIN METHOD
     * ===========================
     */
    public static void main(String[] args) {

        System.out.println("convert(1.0, FEET, INCH) = "
                + convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("convert(3.0, YARD, FEET) = "
                + convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("convert(1.0, CM, INCH) = "
                + convert(1.0, LengthUnit.CM, LengthUnit.INCH));
    }
}