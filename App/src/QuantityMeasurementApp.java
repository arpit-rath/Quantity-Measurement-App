public class QuantityMeasurementApp {

    /*
     * ===========================
     * ENUM: LengthUnit
     * ===========================
     * Base unit = FEET
     */
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),                     // 1 yard = 3 feet
        CM(0.393701 / 12.0);           // 1 cm = 0.393701 inches -> convert to feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
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

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
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
     * MAIN METHOD
     * ===========================
     */
    public static void main(String[] args) {

        System.out.println(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));

        System.out.println(new QuantityLength(1.0, LengthUnit.CM)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }
}