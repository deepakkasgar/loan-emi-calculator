package com.loan.emicalculator.service.impl;

import com.loan.emicalculator.dto.EMIRequest;
import com.loan.emicalculator.dto.EMIResponse;
import com.loan.emicalculator.service.EMIService;
import com.loan.emicalculator.util.EMICalculatorUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EMIServiceImpl implements EMIService {

    @Override
    public EMIResponse calculateEmi(EMIRequest request) {

        BigDecimal monthlyEmi =
                EMICalculatorUtil.calculateEmi(
                        request.getLoanAmount(),
                        request.getAnnualInterestRate(),
                        request.getTenureInYears());

        int months = request.getTenureInYears() * 12;

        BigDecimal totalAmountPayable = monthlyEmi
                .multiply(BigDecimal.valueOf(months))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalInterest = totalAmountPayable
                .subtract(request.getLoanAmount())
                .setScale(2, RoundingMode.HALF_UP);

        return EMIResponse.builder()
                .monthlyEmi(monthlyEmi)
                .totalInterest(totalInterest)
                .totalAmountPayable(totalAmountPayable)
                .build();
    }
}