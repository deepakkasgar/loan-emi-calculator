package com.loan.emicalculator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class EMICalculatorUtil {

    private EMICalculatorUtil() {
    }

    public static BigDecimal calculateEmi(
            BigDecimal principal,
            BigDecimal annualInterestRate,
            int tenureInYears) {

        int months = tenureInYears * 12;

        double p = principal.doubleValue();
        double r = annualInterestRate.doubleValue() / (12 * 100);

        double emi = (p * r * Math.pow(1 + r, months))
                / (Math.pow(1 + r, months) - 1);

        return BigDecimal.valueOf(emi)
                .setScale(2, RoundingMode.HALF_UP);
    }
}