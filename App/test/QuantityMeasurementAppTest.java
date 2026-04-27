import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    /*
     * ===========================
     * YARD TESTS
     * ===========================
     */

    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.QuantityLength(36.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    /*
     * ===========================
     * CENTIMETER TESTS
     * ===========================
     */

    @Test
    void testEquality_CMToCM_SameValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.CM);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.CM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CMToInch_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.CM);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.393701,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CMToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.CM);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    /*
     * ===========================
     * TRANSITIVE PROPERTY TEST
     * ===========================
     */

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var yard = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        var feet = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var inch = new QuantityMeasurementApp.QuantityLength(36.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    /*
     * ===========================
     * NULL & SAFETY TESTS
     * ===========================
     */

    @Test
    void testEquality_NullComparison() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.CM);

        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }

    /*
     * ===========================
     * COMPLEX ALL-UNIT TEST
     * ===========================
     */

    @Test
    void testEquality_AllUnits_ComplexScenario() {

        var yard = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.YARD);

        var feet = new QuantityMeasurementApp.QuantityLength(6.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var inch = new QuantityMeasurementApp.QuantityLength(72.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}