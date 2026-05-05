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
                throw new IllegalArgumentException("Invalid value");
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
         * ===========================
         * UC6: Add (default → first unit)
         * ===========================
         */
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        /*
         * ===========================
         * UC7: Add with TARGET UNIT
         * ===========================
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();

            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        /*
         * STATIC OVERLOADS
         */
        public static QuantityLength add(QuantityLength q1,
                                         QuantityLength q2,
                                         LengthUnit targetUnit) {
            return q1.add(q2, targetUnit);
        }

        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {
            return new QuantityLength(v1, u1)
                    .add(new QuantityLength(v2, u2), targetUnit);
        }

        /*
         * EQUALITY
         */
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
     * MAIN METHOD
     * ===========================
     */
    public static void main(String[] args) {

        var result1 = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET);

        System.out.println(result1); // 2 FEET

        var result2 = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD);

        System.out.println(result2); // ~0.667 YARD
    }
}