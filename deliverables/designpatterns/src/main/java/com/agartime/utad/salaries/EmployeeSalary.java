package com.agartime.utad.salaries;

import java.util.Date;

/**
 * Created by antoniogonzalezartime on 07/01/14.
 */
public class EmployeeSalary {

    private int employeeId;
    private int salary;
    private String beginDate, endDate;
    private int salaryRange;

    public EmployeeSalary(String line) {
        if (EmployeeSalaryMatcher.isEmployeeSalaryPattern(line)) {
            String [] tokens = line.split(EmployeeSalaryMatcher.getSplitChars());
            this.employeeId=Integer.parseInt(tokens[EmployeeSalaryMatcher.getTokenForEmployeeId()]);
            this.salary=Integer.parseInt(tokens[EmployeeSalaryMatcher.getTokenForSalary()]);
            this.beginDate=tokens[EmployeeSalaryMatcher.getTokenForBeginDate()];
            this.endDate=tokens[EmployeeSalaryMatcher.getTokenForEndDate()];
            this.salaryRange = SalaryRange.rangeForSalary(this.salary);
        }
    }

    public boolean isCurrentSalary() {
        return endDate.equals("9999-01-01");
    }

    public boolean earnsBetween(int min, int max) {
        return (this.salary >= min && this.salary <= max);
    }

    public String getRangeDescription() {
        return SalaryRange.getDescription(this.salaryRange);
    }
}
