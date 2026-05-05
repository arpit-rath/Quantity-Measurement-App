import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    private static final double EPS = 1e-6;

    // =========================
    // EQUALITY TESTS
    // =========================

    @Test
    void testEquality_KgToKg_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KgToGram() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_KgToPound() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testEquality_DifferentValue() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(null));
    }

    @Test
    void testEquality_SameReference() {
        var w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w.equals(w));
    }

    // =========================
    // CONVERSION TESTS
    // =========================

    @Test
    void testConversion_KgToGram() {
        var result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_PoundToKg() {
        var result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testConversion_SameUnit() {
        var result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_Zero() {
        var result = new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPS);
    }

    // =========================
    // ADDITION TESTS
    // =========================

    @Test
    void testAddition_SameUnit() {
        var result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit() {
        var result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTarget() {
        var result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_PoundPlusKg() {
        var result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));

        assertEquals(4.40924, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_Zero() {
        var result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_Negative() {
        var result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), EPS);
    }

    // =========================
    // VALIDATION TESTS
    // =========================

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
        });
    }
}