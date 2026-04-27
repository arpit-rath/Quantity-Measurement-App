public class QuantityMeasurementApp {

    /*
     * =======================
     * FEET CLASS (same as UC1)
     * =======================
     */
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different type
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            // Compare double safely
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    /*
     * =======================
     * INCHES CLASS (NEW in UC2)
     * =======================
     */
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different type
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;

            // Safe comparison
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    /*
     * =======================
     * STATIC METHODS (UC2 requirement)
     * =======================
     */

    // Compare Feet values
    public static boolean compareFeet(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    // Compare Inches values
    public static boolean compareInches(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
        return i1.equals(i2);
    }

    /*
     * =======================
     * MAIN METHOD
     * =======================
     */
    public static void main(String[] args) {

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + compareInches(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + compareFeet(1.0, 1.0) + ")");
    }
}