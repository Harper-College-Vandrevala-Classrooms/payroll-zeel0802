package com.csc;

public class Payroll {

    private static final double SOCIAL_SECURITY_TAX = 0.06;
    private static final double FEDERAL_TAX = 0.14;
    private static final double STATE_TAX = 0.05;
    private static final double UNION_DUES = 10.00;

    // Life Insurance Plan Costs
    private static final double NO_PLAN = 0.00;
    private static final double SINGLE_PLAN = 5.00;
    private static final double MARRIED_PLAN = 10.00;
    private static final double MARRIED_WITH_CHILDREN_PLAN = 15.00;

    // Overtime thresholds
    private static final double OVERTIME_THRESHOLD = 40;
    private static final double DOUBLE_OVERTIME_THRESHOLD = 50;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double DOUBLE_OVERTIME_MULTIPLIER = 2.0;

    /**
     * Calculate gross pay with regular, overtime, and double overtime hours
     */
    public static double calculateGrossPay(double hoursWorked, double payRate) {
        double grossPay = 0;

        if (hoursWorked <= OVERTIME_THRESHOLD) {
            // Regular hours only
            grossPay = hoursWorked * payRate;
        } else if (hoursWorked <= DOUBLE_OVERTIME_THRESHOLD) {
            // Overtime but not double-overtime
            double regularHours = OVERTIME_THRESHOLD;
            double overtimeHours = hoursWorked - OVERTIME_THRESHOLD;
            grossPay = (regularHours * payRate) + (overtimeHours * payRate * OVERTIME_MULTIPLIER);
        } else {
            // Double-overtime
            double regularHours = OVERTIME_THRESHOLD;
            double overtimeHours = DOUBLE_OVERTIME_THRESHOLD - OVERTIME_THRESHOLD;
            double doubleOvertimeHours = hoursWorked - DOUBLE_OVERTIME_THRESHOLD;
            grossPay = (regularHours * payRate) +
                       (overtimeHours * payRate * OVERTIME_MULTIPLIER) +
                       (doubleOvertimeHours * payRate * DOUBLE_OVERTIME_MULTIPLIER);
        }

        return Math.round(grossPay * 100.0) / 100.0;  // Ensure rounding to 2 decimal places
    }

    /**
     * Calculate insurance based on the number of dependents
     */
    public static double calculateInsurance(int dependents) {
        return dependents >= 3 ? 35.00 : 15.00;
    }

    /**
     * Get life insurance cost based on the selected plan
     */
    public static double getLifeInsuranceCost(int plan) {
        switch (plan) {
            case 2:
                return SINGLE_PLAN;
            case 3:
                return MARRIED_PLAN;
            case 4:
                return MARRIED_WITH_CHILDREN_PLAN;
            default:
                return NO_PLAN;
        }
    }

    /**
     * Calculate total deductions (taxes, union dues, insurance, life insurance, retirement)
     */
    public static double calculateDeductions(double grossPay, int dependents, int lifeInsurancePlan, boolean contributeToRetirement) {
        double insurance = calculateInsurance(dependents);
        double lifeInsurance = getLifeInsuranceCost(lifeInsurancePlan);
        double retirementContribution = contributeToRetirement ? grossPay * 0.05 : 0.0;

        double totalTaxes = (grossPay * SOCIAL_SECURITY_TAX) +
                            (grossPay * FEDERAL_TAX) +
                            (grossPay * STATE_TAX);

        double deductions = totalTaxes + UNION_DUES + insurance + lifeInsurance + retirementContribution;
        return Math.round(deductions * 100.0) / 100.0;  // Ensure rounding to 2 decimal places
    }

    /**
     * Calculate net pay after all deductions
     */
    public static double calculateNetPay(double grossPay, double deductions) {
        double netPay = grossPay - deductions;
        return Math.round(netPay * 100.0) / 100.0;  // Ensure rounding to 2 decimal places
    }

}
