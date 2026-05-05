import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityUC13Test {

    private static final double EPS = 1e-6;

    // =========================
    // ADD (UNCHANGED)
    // =========================

    @Test
    void testAdd_BehaviorPreserved() {
        var result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCH));

        assertEquals(2.0, result.getValue(), EPS);
    }

    // =========================
    // SUBTRACT (UNCHANGED)
    // =========================

    @Test
    void testSubtract_BehaviorPreserved() {
        var result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCH));

        assertEquals(9.5, result.getValue(), EPS);
    }

    // =========================
    // DIVIDE (UNCHANGED)
    // =========================

    @Test
    void testDivide_BehaviorPreserved() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPS);
    }

    // =========================
    // VALIDATION CONSISTENCY
    // =========================

    @Test
    void testNullOperand_AllOperations() {

        var q = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    @Test
    void testCrossCategory_AllOperations() {

        var length = new Quantity<>(10.0, LengthUnit.FEET);
        var weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> length.add(weight));
        assertThrows(IllegalArgumentException.class, () -> length.subtract(weight));
        assertThrows(IllegalArgumentException.class, () -> length.divide(weight));
    }

    // =========================
    // ENUM OPERATION TESTS
    // =========================

    @Test
    void testDivide_ByZero() {
        assertThrows(ArithmeticException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(new Quantity<>(0.0, LengthUnit.FEET));
        });
    }

    // =========================
    // IMMUTABILITY
    // =========================

    @Test
    void testImmutability() {

        var q1 = new Quantity<>(10.0, LengthUnit.FEET);
        var q2 = new Quantity<>(5.0, LengthUnit.FEET);

        var result = q1.subtract(q2);

        assertEquals(10.0, q1.getValue()); // unchanged
        assertEquals(5.0, q2.getValue());  // unchanged
        assertEquals(5.0, result.getValue());
    }

    // =========================
    // ROUNDING
    // =========================

    @Test
    void testRounding_AddSubtract() {

        var result = new Quantity<>(1.333, LengthUnit.FEET)
                .subtract(new Quantity<>(0.111, LengthUnit.FEET));

        assertEquals(1.22, result.getValue(), 1e-2);
    }
}