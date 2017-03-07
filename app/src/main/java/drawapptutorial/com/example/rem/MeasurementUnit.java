package drawapptutorial.com.example.rem;

public enum MeasurementUnit {
    MILLILITER      ("Milliliter",          1.0),
    LITER           ("Liter",               1000.0),
    GRAM            ("Gram",                1.0),
    KILOGRAM        ("Kilogram",            1000.0),
    FLUID_OUNCE_US  ("Fluid Ounce (US)",    29.5735295625),
    FLUID_OUNCE_IMP ("Fluid Ounce (IMP)",   28.4130625),
    TEASPOON        ("Teaspoon",            5.0),
    TEASPOON_US     ("Teaspoon (US)",       4.92892159375),
    TABLESPOON      ("Tablespoon",          15.0),
    TABLESPOON_US   ("Tablespoon (US)",     14.7867647812),
    CUP             ("Cup",                 250.0),
    CUP_US          ("Cup (US)",            14.7867647812),
    CUP_IMP         ("Cup (IMP)",           284.1),
    PINT            ("Pint",                570.0),
    PINT_US         ("Pint (US)",           473.18),
    PINT_IMP        ("Pint (IMP)",          568.26),
    QUART_US        ("Quart (US)",          946.35),
    QUART_IMP       ("Quart (IMP)",         1136.5225),
    GALLON_US       ("Gallon (US)",         3785.41),
    GALLON_IMP      ("Gallon (IMP)",        4546.09),

    NONE            ("N/A",                 0.0);

    public static MeasurementUnit[] array = {
            MILLILITER, LITER, GRAM, KILOGRAM, FLUID_OUNCE_US, FLUID_OUNCE_IMP, TEASPOON, TEASPOON_US, TABLESPOON, TABLESPOON_US,
            CUP, CUP_US, CUP_IMP, PINT, PINT_US, PINT_IMP, QUART_US, QUART_IMP, GALLON_US, GALLON_IMP
    };

    public static String[] nameArray;
    static {
        String[] nameArray = new String[array.length];
        for (int i = 0; i < array.length; i++)
            nameArray[i] = array[i].name;
        MeasurementUnit.nameArray = nameArray;
    }

    public String name;
    public double milliliters;

    MeasurementUnit(String name, double milliliters) {
        this.name = name;
        this.milliliters = milliliters;
    }

    public static MeasurementUnit fromString(String str) {
        for (MeasurementUnit unit : MeasurementUnit.values())
            if (unit.name.equals(str))
                return unit;
        return NONE;
    }
}
