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
         * ADD METHOD (CORE UC6)
         * ===========================
         * Result in unit of FIRST operand
         */
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();

            double result = this.unit.fromFeet(sumInFeet);

            return new QuantityLength(result, this.unit);
        }

        /*
         * STATIC ADD (optional overload)
         */
        public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
            return q1.add(q2);
        }

        /*
         * STATIC ADD (raw values overload)
         */
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2) {
            return new QuantityLength(v1, u1)
                    .add(new QuantityLength(v2, u2));
        }

        /*
         * EQUALITY (UC3+)
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

        QuantityLength result1 =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCH));

        System.out.println(result1); // 2 FEET

        QuantityLength result2 =
                new QuantityLength(1.0, LengthUnit.YARD)
                        .add(new QuantityLength(3.0, LengthUnit.FEET));

        System.out.println(result2); // 2 YARD
    }
}