import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    private static final double EPS = 1e-6;

    // =========================
    // LENGTH TESTS
    // =========================

    @Test
    void testLengthEquality() {
        assertTrue(new Quantity<>(1.0, LengthUnit.FEET)
                .equals(new Quantity<>(12.0, LengthUnit.INCH)));
    }

    @Test
    void testLengthConversion() {
        var result = new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    void testLengthAddition() {
        var result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), EPS);
    }

    // =========================
    // WEIGHT TESTS
    // =========================

    @Test
    void testWeightEquality() {
        assertTrue(new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testWeightConversion() {
        var result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testWeightAddition() {
        var result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPS);
    }

    // =========================
    // CROSS CATEGORY TEST
    // =========================

    @Test
    void testCrossCategoryComparison() {
        var length = new Quantity<>(1.0, LengthUnit.FEET);
        var weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // =========================
    // VALIDATION TESTS
    // =========================

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }
}