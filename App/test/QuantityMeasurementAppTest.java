import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VolumeTest {

    private static final double EPS = 1e-6;

    // =========================
    // EQUALITY TESTS
    // =========================

    @Test
    void testEquality_LitreToLitre() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_LitreToGallon() {
        assertTrue(new Quantity<>(3.78541, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.GALLON)));
    }

    @Test
    void testEquality_Null() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    // =========================
    // CONVERSION TESTS
    // =========================

    @Test
    void testConversion_LitreToMillilitre() {
        var result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_GallonToLitre() {
        var result = new Quantity<>(1.0, VolumeUnit.GALLON)
                .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), 1e-3);
    }

    @Test
    void testConversion_SameUnit() {
        var result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPS);
    }

    // =========================
    // ADDITION TESTS
    // =========================

    @Test
    void testAddition_SameUnit() {
        var result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit() {
        var result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_GallonPlusLitre() {
        var result = new Quantity<>(1.0, VolumeUnit.GALLON)
                .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

        assertEquals(2.0, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTarget() {
        var result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                        VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    // =========================
    // EDGE CASES
    // =========================

    @Test
    void testAddition_Zero() {
        var result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_Negative() {
        var result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue(), EPS);
    }

    // =========================
    // CROSS CATEGORY TEST
    // =========================

    @Test
    void testVolumeVsLength() {
        var volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        var length = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    void testVolumeVsWeight() {
        var volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        var weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }
}