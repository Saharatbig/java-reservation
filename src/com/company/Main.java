package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String dateForCheckTable = "08-06-2020";
        String[] reservationDetail = {
                // {"name","phone-number","check-in-date","check-in-time","check-out-time","amount-of-customers"}
                "Mr.reservation","0888888888","08-06-2020","15:00:00","15:30:00","5"
        };
        int reservationTable =  0;

        reservationTable = reservationOrder(dateForCheckTable,reservationDetail);
        System.out.println("On date " + dateForCheckTable + " you must use " + reservationTable + " tables.");
    }

    public static int reservationOrder(String dateForCheckTable, String[] reservationOrder){
        DatetimeUtils checkDateTimeFormat = new DatetimeUtils();
        int totoalTable = 0;
        int duplicateTimeTable = 0;
        double maxPersonPerOrder = 0;
        int maxPerson = 0;
        int maxTablePerOrder = 0 ;
        int duplicateTime = 0;
        String[][] duplicateRangeOfTime;

        //Mock data
        String[][] allReservation = {
                // {"name","phone-number","check-in-date","check-in-time","check-out-time","amount-of-customers"}
                {"Mr.reservation1","1111111111","08-06-2020","15:00:00","15:30:00","4"},
                {"Mr.reservation2","2222222222","08-06-2020","15:00:00","15:30:00","4"},
                {"Mr.reservation3","3333333333","09-06-2020","16:00:00","16:30:00","20"},
                {"Mr.reservation4","4444444444","10-06-2020","15:00:00","15:30:00","5"},
                {"Mr.reservation5","5555555555","11-06-2020","15:00:00","15:30:00","4"}
        };

        String[][] addedReservation = {reservationOrder};
        allReservation = append(allReservation, addedReservation);

        // check all reservation and dateForCheckTable
        String[][] data = Arrays.stream(allReservation).filter(x -> (checkDateTimeFormat.checkSameDate(x[2],dateForCheckTable)) == true ).toArray(String[][]::new);

        if(data.length > 0) {
            for (int i = 0; i < data.length; i++) {
                maxPerson = (int) Math.max((Double.parseDouble(data[i][5])), maxPersonPerOrder);
                maxPersonPerOrder = maxPerson;
                for (int j = data.length - 1; j >= 1 + i; j--) {
                    if (checkDateTimeFormat.IsInRageOfTime(data[i][3], data[i][4], data[j][3], data[j][4]) == true) {
                        duplicateTime = duplicateTime+1;
                        duplicateTimeTable = duplicateTimeTable + (int) (Math.ceil((Double.parseDouble(data[i][5]) / 4)) + (int) (Math.ceil((Double.parseDouble(data[j][5]) / 4))) );
                    }
                }
                if( 2*duplicateTime == i*(i+1)) {
                    duplicateTime = i;
                }
            }
            maxTablePerOrder = (int) Math.ceil(maxPersonPerOrder / 4);

            totoalTable = Math.max(duplicateTimeTable/duplicateTime,maxTablePerOrder);

            return totoalTable;
        }

        return 0;
    }

    public static String[][] append(String[][] a, String[][] b) {
        String[][] result = new String[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
