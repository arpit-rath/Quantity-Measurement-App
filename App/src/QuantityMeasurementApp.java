// =========================
// WeightUnit.java
// =========================
public enum WeightUnit {

    KILOGRAM(1.0),     // base unit
    GRAM(0.001),       // 1 g = 0.001 kg
    POUND(0.453592);   // 1 lb ≈ 0.453592 kg

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    // Convert value → kilograms (base)
    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    // Convert kilograms → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKgFactor;
    }

    public double getConversionFactor() {
        return toKgFactor;
    }
}

// =========================
// QuantityWeight.java
// =========================
public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
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

    public WeightUnit getUnit() {
        return unit;
    }

    // Convert to target unit
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseKg = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseKg);
        return new QuantityWeight(converted, targetUnit);
    }

    // Add (default → result in first operand’s unit)
    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    // Add with explicit target unit
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumKg =
                this.unit.convertToBaseUnit(this.value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sumKg);
        return new QuantityWeight(result, targetUnit);
    }

    // Equality (cross-unit via base unit)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(
                this.unit.convertToBaseUnit(this.value),
                other.unit.convertToBaseUnit(other.value)
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

// =========================
// QuantityMeasurementApp.java (Demo)
// =========================
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        var w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); // true

        System.out.println(w1.convertTo(WeightUnit.GRAM));
        // Quantity(1000.0, GRAM)

        System.out.println(w1.add(w2));
        // Quantity(2.0, KILOGRAM)

        System.out.println(w1.add(w2, WeightUnit.GRAM));
        // Quantity(2000.0, GRAM)
    }
}