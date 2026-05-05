// =========================
// VolumeUnit.java
// =========================
public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}

// =========================
// Add inside main()
// =========================

// VOLUME
var v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
var v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
var v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

// Equality
System.out.println(v1.equals(v2)); // true

// Conversion
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE)); // 1000 mL

// Addition
        System.out.println(v1.add(v2)); // 2 L

// Explicit unit
        System.out.println(v1.add(v3, VolumeUnit.MILLILITRE));