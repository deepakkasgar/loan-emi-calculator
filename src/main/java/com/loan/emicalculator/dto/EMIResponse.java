package com.loan.emicalculator.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EMIResponse {

    private BigDecimal monthlyEmi;

    private BigDecimal totalInterest;

    private BigDecimal totalAmountPayable;

}