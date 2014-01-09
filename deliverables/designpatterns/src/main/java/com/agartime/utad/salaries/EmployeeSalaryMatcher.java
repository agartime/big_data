package com.agartime.utad.salaries;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class EmployeeSalaryMatcher {
    private final static String STANDARD_PATTERN="\\d+,\\d+,'\\d+-\\d+-\\d+','\\d+-\\d+-\\d+'";
    private enum TokenNumbers {EMP_ID,SALARY,NULO,BEGIN_DATE,NULO2,NULO3,END_DATE}

    public static boolean isEmployeeSalaryPattern(String line) {
        return line.matches(STANDARD_PATTERN);
    }

    public static String getSplitChars() {
        return ",|\'";
    }

    public static int getTokenForEmployeeId() {
        return TokenNumbers.EMP_ID.ordinal();
    }

    public static int getTokenForSalary() {
        return TokenNumbers.SALARY.ordinal();
    }

    public static int getTokenForBeginDate() {
        return TokenNumbers.BEGIN_DATE.ordinal();
    }

    public static int getTokenForEndDate() {
        return TokenNumbers.END_DATE.ordinal();
    }

}
