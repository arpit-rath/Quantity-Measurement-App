import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    /*
     * Test: Same values should be equal
     */
    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    /*
     * Test: Different values should not be equal
     */
    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    /*
     * Test: Comparing with null should return false
     */
    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null), "Comparison with null should return false");
    }

    /*
     * Test: Comparing with different type (non-numeric object)
     */
    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals("Not a Feet object"),
                "Comparison with non-Feet object should return false");
    }

    /*
     * Test: Same reference should be equal (reflexive property)
     */
    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1), "Object should be equal to itself");
    }
}