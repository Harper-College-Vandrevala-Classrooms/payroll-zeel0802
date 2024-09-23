package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestPayroll {

    @Test
    public void testGrossPayRegularHours() {
        double payRate = 16.78;  // Regular pay rate
        assertEquals(671.20, Payroll.calculateGrossPay(40, payRate), 0.01);
    }

    @Test
    public void testGrossPayOvertime() {
        double payRate = 16.78;
        assertEquals(721.54, Payroll.calculateGrossPay(42, payRate), 0.01);
    }

    @Test
    public void testGrossPayDoubleOvertime() {
        double payRate = 16.78;
        assertEquals(990.02, Payroll.calculateGrossPay(52, payRate), 0.01);  // Updated expected value for double-overtime
    }

    @Test
    public void testInsuranceWithThreeDependents() {
        assertEquals(35.00, Payroll.calculateInsurance(3), 0.01);
    }

    @Test
    public void testInsuranceWithLessThanThreeDependents() {
        assertEquals(15.00, Payroll.calculateInsurance(2), 0.01);
    }

    @Test
    public void testLifeInsuranceCostSinglePlan() {
        assertEquals(5.00, Payroll.getLifeInsuranceCost(2), 0.01);
    }

    @Test
    public void testLifeInsuranceCostMarriedPlan() {
        assertEquals(10.00, Payroll.getLifeInsuranceCost(3), 0.01);
    }

    @Test
    public void testLifeInsuranceCostMarriedWithChildrenPlan() {
        assertEquals(15.00, Payroll.getLifeInsuranceCost(4), 0.01);
    }

    @Test
    public void testDeductionsWithLifeInsurance() {
        double grossPay = 754.91;
        int dependents = 2;
        int lifeInsurancePlan = 2; // Single Plan
        boolean contributeToRetirement = false; // No retirement plan
        assertEquals(218.73, Payroll.calculateDeductions(grossPay, dependents, lifeInsurancePlan, contributeToRetirement), 0.01); 
    }

    @Test
    public void testDeductionsWithLifeInsuranceAndRetirement() {
        double grossPay = 754.91;
        int dependents = 2;
        int lifeInsurancePlan = 2; // Single Plan
        boolean contributeToRetirement = true;  // Retirement contribution
        assertEquals(256.47, Payroll.calculateDeductions(grossPay, dependents, lifeInsurancePlan, contributeToRetirement), 0.01);  
    }

    @Test
    public void testNetPayWithLifeInsurance() {
        double grossPay = 754.91;
        double deductions = 218.73;
        assertEquals(536.18, Payroll.calculateNetPay(grossPay, deductions), 0.01);
    }

    @Test
    public void testNetPayWithLifeInsuranceAndRetirement() {
        double grossPay = 754.91;
        double deductions = 256.47;
        assertEquals(498.44, Payroll.calculateNetPay(grossPay, deductions), 0.01);  // Net pay with retirement
    }
}
