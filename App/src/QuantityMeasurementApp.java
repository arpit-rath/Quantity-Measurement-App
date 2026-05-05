// =========================
// Quantity.java (UC13 DRY)
// =========================
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // =========================
    // 🔥 ARITHMETIC ENUM (DRY CORE)
    // =========================
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.operation = op;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    // =========================
    // 🔥 VALIDATION HELPER
    // =========================
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean requireTarget) {

        if (other == null)
            throw new IllegalArgumentException("Null operand");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        if (requireTarget && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // =========================
    // 🔥 CENTRALIZED CORE METHOD
    // =========================
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {

        double base1 = this.toBase();
        double base2 = other.toBase();

        return op.compute(base1, base2);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // =========================
    // ADD
    // =========================
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), targetUnit);
    }

    // =========================
    // SUBTRACT
    // =========================
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), targetUnit);
    }

    // =========================
    // DIVIDE
    // =========================
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // =========================
    // EQUALITY
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}