package com.loan.emicalculator.controller;


import com.loan.emicalculator.dto.EMIRequest;
import com.loan.emicalculator.dto.EMIResponse;
import com.loan.emicalculator.service.EMIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emi")
@RequiredArgsConstructor
public class EMIController {

    private final EMIService emiService;

    @PostMapping("/calculate")
    public ResponseEntity<EMIResponse> calculateEmi(@Valid @RequestBody EMIRequest request) {

        return ResponseEntity.ok(emiService.calculateEmi(request));
    }

}