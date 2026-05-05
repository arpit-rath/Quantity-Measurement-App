// =========================
// IMeasurable.java
// =========================
public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}


// =========================
// LengthUnit.java
// =========================
public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}


// =========================
// WeightUnit.java
// =========================
public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}


// =========================
// Quantity.java (GENERIC)
// =========================
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

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
    // CONVERT
    // =========================
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit null");

        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // =========================
    // ADD
    // =========================
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Null operand");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit null");

        double sum = this.toBase() + other.toBase();

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), targetUnit);
    }

    // =========================
    // EQUALITY
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
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

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}


// =========================
// QuantityMeasurementApp.java
// =========================
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // LENGTH
        var l1 = new Quantity<>(1.0, LengthUnit.FEET);
        var l2 = new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println(l1.equals(l2));
        System.out.println(l1.convertTo(LengthUnit.INCH));
        System.out.println(l1.add(l2, LengthUnit.FEET));

        // WEIGHT
        var w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));

        // CROSS CATEGORY (should be false)
        System.out.println(l1.equals(w1));
    }
}