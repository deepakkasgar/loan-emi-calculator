package com.loan.emicalculator.service;

import com.loan.emicalculator.dto.EMIRequest;
import com.loan.emicalculator.dto.EMIResponse;

public interface EMIService {
    EMIResponse calculateEmi(EMIRequest request);

}