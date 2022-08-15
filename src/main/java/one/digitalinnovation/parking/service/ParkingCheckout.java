package one.digitalinnovation.parking.service;


import one.digitalinnovation.parking.model.Parking;
import org.aspectj.apache.bcel.generic.RET;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {

    public static final int TOLERANCE = 20;
    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24*ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 20.00;

    public static Double getBill(Parking parking){
        return getBill(parking.getEntryDate(),parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if (minutes <= TOLERANCE ){
            return bill;
        }
        if (minutes<= ONE_HOUR ){
            return ONE_HOUR_VALUE;
        } else if (minutes <= TWENTY_FOUR_HOUR){
            bill = ONE_HOUR_VALUE;
            int hour = (int)(minutes/ONE_HOUR);
            for (int i = 0; i < hour; i++ ){
                bill += ADDITIONAL_PER_HOUR_VALUE;
            }
        } else {
            int days = (int) (minutes/TWENTY_FOUR_HOUR);

            for (int i = 0; i < days; i++ ){
                bill +=DAY_VALUE;
            }
        }
        return bill;
    }

}
