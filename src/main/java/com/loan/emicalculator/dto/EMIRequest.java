package com.loan.emicalculator.dto;

import com.loan.emicalculator.enums.LoanType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EMIRequest {

    @NotNull(message = "Loan type is required")
    private LoanType loanType;

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than zero")
    private BigDecimal loanAmount;

    @NotNull(message = "Annual interest rate is required")
    @DecimalMin(value = "0.01", message = "Interest rate must be greater than 0")
    @DecimalMax(value = "100.00", message = "Interest rate cannot exceed 100%")
    private BigDecimal annualInterestRate;

    @NotNull(message = "Loan tenure is required")
    @Positive(message = "Loan tenure must be greater than zero")
    @Max(value = 40, message = "Loan tenure cannot exceed 40 years")
    private Integer tenureInYears;

}