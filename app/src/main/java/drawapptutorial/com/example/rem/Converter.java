package drawapptutorial.com.example.rem;

public class Converter {
    public static double convert(MeasurementUnit unitFrom, MeasurementUnit unitTo) {
        return convert(unitFrom, unitTo, 1.0);
    }

    public static double convert(MeasurementUnit unitFrom, MeasurementUnit unitTo, double quantityFrom) {
        return quantityFrom * unitTo.milliliters / unitFrom.milliliters;
    }
}
