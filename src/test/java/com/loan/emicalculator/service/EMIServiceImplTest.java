package com.loan.emicalculator.service;

import com.loan.emicalculator.dto.EMIRequest;
import com.loan.emicalculator.dto.EMIResponse;
import com.loan.emicalculator.enums.LoanType;
import com.loan.emicalculator.service.impl.EMIServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EMIServiceImplTest {

    private EMIServiceImpl emiService;

    @BeforeEach
    void setUp() {
        emiService = new EMIServiceImpl();
    }

    @Test
    @DisplayName("Should calculate EMI for Home Loan")
    void shouldCalculateHomeLoanEmi() {

        EMIRequest request = EMIRequest.builder()
                .loanType(LoanType.HOME)
                .loanAmount(BigDecimal.valueOf(1500000))
                .annualInterestRate(BigDecimal.valueOf(8.85))
                .tenureInYears(15)
                .build();

        EMIResponse response = emiService.calculateEmi(request);

        assertNotNull(response);

        assertEquals(new BigDecimal("15080.44"),response.getMonthlyEmi());

        assertEquals(new BigDecimal("1214479.20"),response.getTotalInterest());

        assertEquals(new BigDecimal("2714479.20"),response.getTotalAmountPayable());

    }

    @Test
    void shouldCalculateCarLoanEmi() {

        EMIRequest request = EMIRequest.builder()
                .loanType(LoanType.CAR)
                .loanAmount(BigDecimal.valueOf(800000))
                .annualInterestRate(BigDecimal.valueOf(9.50))
                .tenureInYears(7)
                .build();

        EMIResponse response = emiService.calculateEmi(request);

        assertNotNull(response);
        assertTrue(response.getMonthlyEmi().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(response.getTotalInterest().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(response.getTotalAmountPayable().compareTo(request.getLoanAmount()) > 0);
    }

    @Test
    void shouldCalculatePersonalLoanEmi() {

        EMIRequest request = EMIRequest.builder()
                .loanType(LoanType.PERSONAL)
                .loanAmount(BigDecimal.valueOf(500000))
                .annualInterestRate(BigDecimal.valueOf(12))
                .tenureInYears(5)
                .build();

        EMIResponse response = emiService.calculateEmi(request);

        assertNotNull(response);
        assertTrue(response.getMonthlyEmi().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(response.getTotalInterest().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(response.getTotalAmountPayable().compareTo(request.getLoanAmount()) > 0);
    }

    @Test
    void shouldReturnTotalAmountGreaterThanPrincipal() {

        EMIRequest request = EMIRequest.builder()
                .loanType(LoanType.HOME)
                .loanAmount(BigDecimal.valueOf(1000000))
                .annualInterestRate(BigDecimal.valueOf(8.5))
                .tenureInYears(10)
                .build();

        EMIResponse response = emiService.calculateEmi(request);

        assertTrue(response.getTotalAmountPayable()
                .compareTo(request.getLoanAmount()) > 0);
    }

    @Test
    void shouldCalculateTotalInterestCorrectly() {

        EMIRequest request = EMIRequest.builder()
                .loanType(LoanType.HOME)
                .loanAmount(BigDecimal.valueOf(1000000))
                .annualInterestRate(BigDecimal.valueOf(8.5))
                .tenureInYears(10)
                .build();

        EMIResponse response = emiService.calculateEmi(request);

        BigDecimal expectedInterest = response.getTotalAmountPayable()
                .subtract(request.getLoanAmount());

        assertEquals(expectedInterest, response.getTotalInterest());
    }
}