import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    /*
     * ===========================
     * SAME UNIT TESTS
     * ===========================
     */

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var result = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        var result = new QuantityMeasurementApp.QuantityLength(6.0,
                QuantityMeasurementApp.LengthUnit.INCH)
                .add(new QuantityMeasurementApp.QuantityLength(6.0,
                        QuantityMeasurementApp.LengthUnit.INCH));

        assertEquals(12.0, result.getValue(), EPS);
    }

    /*
     * ===========================
     * CROSS UNIT TESTS
     * ===========================
     */

    @Test
    void testAddition_FeetPlusInches() {
        var result = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_InchesPlusFeet() {
        var result = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH)
                .add(new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_YardPlusFeet() {
        var result = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARD)
                .add(new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CmPlusInch() {
        var result = new QuantityMeasurementApp.QuantityLength(2.54,
                QuantityMeasurementApp.LengthUnit.CM)
                .add(new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH));

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    /*
     * ===========================
     * EDGE CASES
     * ===========================
     */

    @Test
    void testAddition_WithZero() {
        var result = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.LengthUnit.INCH));

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var result = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(-2.0,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_Commutativity() {

        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    /*
     * ===========================
     * EXCEPTION TESTS
     * ===========================
     */

    @Test
    void testAddition_NullSecondOperand() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            q.add(null);
        });
    }

    /*
     * ===========================
     * LARGE & SMALL VALUES
     * ===========================
     */

    @Test
    void testAddition_LargeValues() {
        var result = new QuantityMeasurementApp.QuantityLength(1e6,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(1e6,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(2e6, result.getValue(), EPS);
    }

    @Test
    void testAddition_SmallValues() {
        var result = new QuantityMeasurementApp.QuantityLength(0.001,
                QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.QuantityLength(0.002,
                        QuantityMeasurementApp.LengthUnit.FEET));

        assertEquals(0.003, result.getValue(), EPS);
    }
}