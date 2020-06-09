package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtils {
    // default constructor
    public DatetimeUtils() {

    }

    public static Boolean IsInRageOfTime(String checkInTime,String checkOutTime,String startTime,String endTime) {
        try {
            // startTime
            Date startDateTime = new SimpleDateFormat("HH:mm:ss").parse(startTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(startDateTime);
            calendar1.add(Calendar.DATE, 1);


            // endTime
            Date endDateTime = new SimpleDateFormat("HH:mm:ss").parse(endTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(endDateTime);
            calendar2.add(Calendar.DATE, 1);

            // checkInTime
            Date checkInDateTime = new SimpleDateFormat("HH:mm:ss").parse(checkInTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkInDateTime);
            calendar3.add(Calendar.DATE, 1);

            // checkOutTime
            Date checkOutDateTime = new SimpleDateFormat("HH:mm:ss").parse(checkOutTime);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTime(checkOutDateTime);
            calendar4.add(Calendar.DATE, 1);

            Date start = calendar1.getTime();
            Date end = calendar2.getTime();
            Date checkIn = calendar3.getTime();
            Date checkOut = calendar4.getTime();

            if ((checkIn.compareTo(start) >= 0 && checkIn.compareTo(end) <= 0) &&
                    (checkOut.compareTo(start) >= 0 && checkOut.compareTo(end) <= 0)) {
                return  true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static boolean checkSameDate(String checkInDate, String allReservationDate) {
        boolean sameDay = false;
        try {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(checkInDate);
            Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(allReservationDate);
            cal1.setTime(date1);
            cal2.setTime(date2);
            sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            return sameDay;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sameDay;
    };
}
