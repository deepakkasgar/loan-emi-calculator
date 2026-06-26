package com.loan.emicalculator.controller;

import com.loan.emicalculator.dto.EMIRequest;
import com.loan.emicalculator.dto.EMIResponse;
import com.loan.emicalculator.enums.LoanType;
import com.loan.emicalculator.exception.InvalidLoanException;
import com.loan.emicalculator.service.EMIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EMIControllerTest {

    @Mock
    private EMIService emiService;

    @InjectMocks
    private EMIController emiController;

    private EMIRequest request;
    private EMIResponse response;

    @BeforeEach
    void setUp() {

        request = EMIRequest.builder()
                .loanType(LoanType.HOME)
                .loanAmount(BigDecimal.valueOf(1500000))
                .annualInterestRate(BigDecimal.valueOf(8.85))
                .tenureInYears(15)
                .build();

        response = EMIResponse.builder()
                .monthlyEmi(BigDecimal.valueOf(15080.44))
                .totalInterest(BigDecimal.valueOf(1214479.20))
                .totalAmountPayable(BigDecimal.valueOf(2714479.20))
                .build();
    }

    @Test
    void shouldCalculateEmiSuccessfully() {

        when(emiService.calculateEmi(request)).thenReturn(response);

        ResponseEntity<EMIResponse> result = emiController.calculateEmi(request);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(emiService, times(1)).calculateEmi(request);
    }

    @Test
    void shouldThrowInvalidLoanException() {

        when(emiService.calculateEmi(request)).thenThrow(new InvalidLoanException("Invalid Loan"));

        InvalidLoanException exception = assertThrows(
                InvalidLoanException.class,
                () -> emiController.calculateEmi(request));

        assertEquals("Invalid Loan", exception.getMessage());
        verify(emiService).calculateEmi(request);
    }
}