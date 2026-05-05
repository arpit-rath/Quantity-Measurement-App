import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    /*
     * ===========================
     * BASIC TARGET UNIT TESTS
     * ===========================
     */

    @Test
    void testAddition_TargetFeet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetInches() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetYards() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_TargetCM() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.CM);

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    /*
     * ===========================
     * SAME / DIFFERENT TARGET CASES
     * ===========================
     */

    @Test
    void testAddition_TargetSameAsFirst() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.YARD),
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetSameAsSecond() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.YARD),
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPS);
    }

    /*
     * ===========================
     * COMMUTATIVITY
     * ===========================
     */

    @Test
    void testAddition_Commutativity_TargetUnit() {

        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = a.add(b, QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = b.add(a, QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(r1.equals(r2));
    }

    /*
     * ===========================
     * EDGE CASES
     * ===========================
     */

    @Test
    void testAddition_WithZero_TargetYard() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(1.6666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_Negative_TargetInch() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPS);
    }

    /*
     * ===========================
     * EXCEPTION TESTS
     * ===========================
     */

    @Test
    void testAddition_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.QuantityLength.add(
                    new QuantityMeasurementApp.QuantityLength(1.0,
                            QuantityMeasurementApp.LengthUnit.FEET),
                    new QuantityMeasurementApp.QuantityLength(12.0,
                            QuantityMeasurementApp.LengthUnit.INCH),
                    null);
        });
    }

    @Test
    void testAddition_NullOperand() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            q.add(null, QuantityMeasurementApp.LengthUnit.FEET);
        });
    }
}