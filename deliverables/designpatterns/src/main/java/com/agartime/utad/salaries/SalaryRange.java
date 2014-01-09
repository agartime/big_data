package com.agartime.utad.salaries;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class SalaryRange {

    private static final String UNKNOWN_RANGE = "Unknown Range";
    private static final int [] TOP_RANGES={Integer.MIN_VALUE,30000,40000,50000,60000,Integer.MAX_VALUE};
    private static final String [] RANGE_DESCRIPTION=
            {"(... - 0 USD)",
            "[0 USD - 30.000 USD]",
            "[30.000 USD - 39.999 USD]",
            "[40.000 USD - 49.000 USD]",
            "[50.000 USD - 59.999 USD]",
            "[60.000 USD - ...)"};

    public static int rangeForSalary(int salary) {
        for (int i=0;i<TOP_RANGES.length;i++) {
            if (salary<TOP_RANGES[i]) {
               return i;
            }
        }
        return -1;
    }

    public static int getTopSalaryForRange(int range) {
        return (range>0) ? TOP_RANGES[range] : -1 ;
    }

    public static String getDescription(int range) {
        return (range>0) ? RANGE_DESCRIPTION[range] : UNKNOWN_RANGE;
    }

}
