// Main application class
public class QuantityMeasurementApp {

    /*
     * Inner class Feet represents a measurement in feet.
     * It is immutable (value is final and private).
     */
    public static class Feet {

        // Encapsulated value
        private final double value;

        /*
         * Constructor to initialize the feet value
         */
        public Feet(double value) {
            this.value = value;
        }

        /*
         * Getter method (optional, useful for debugging or extension)
         */
        public double getValue() {
            return value;
        }

        /*
         * Overriding equals() method to compare two Feet objects
         */
        @Override
        public boolean equals(Object obj) {

            // Step 1: Check if both references point to same object
            if (this == obj) {
                return true;
            }

            // Step 2: Check if object is null or of different class
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Step 3: Cast safely
            Feet other = (Feet) obj;

            // Step 4: Compare double values using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }

        /*
         * Overriding hashCode() when equals() is overridden (Best Practice)
         */
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    /*
     * Main method to test manually
     */
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f2) + ")");
    }
}