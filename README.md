# Loan EMI Calculator

A Spring Boot REST API to calculate the Equated Monthly Installment (EMI) for different loan types.

## Overview

This application calculates:

* Monthly EMI
* Total Interest
* Total Amount Payable

Supported loan types:

* HOME
* CAR
* PERSONAL

## Technology Stack

* Java 21
* Spring Boot 3.5.x
* Maven
* Lombok
* Jakarta Bean Validation
* JUnit 5

## Project Structure

```text
src
├── controller
├── dto
├── enums
├── exception
├── service
│   └── impl
└── util
```

## API Endpoint

POST `/api/v1/emi/calculate`

### Sample Request

```json
{
  "loanType": "HOME",
  "loanAmount": 1500000,
  "annualInterestRate": 8.85,
  "tenureInYears": 15
}
```

### Sample Response

```json
{
  "monthlyEmi": 15080.44,
  "totalInterest": 1214479.20,
  "totalAmountPayable": 2714479.20
}
```

## Validation Rules

| Field                | Rule                   |
| -------------------- | ---------------------- |
| Loan Type            | HOME, CAR, PERSONAL    |
| Loan Amount          | Greater than 0         |
| Annual Interest Rate | Between 0.01 and 100   |
| Loan Tenure          | Between 1 and 40 years |

### Sample Validation Error

```json
{
  "timestamp": "2026-06-26T19:30:25",
  "status": 400,
  "error": "Validation Failed",
  "details": {
    "tenureInYears": "Loan tenure cannot exceed 40 years"
  }
}
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## Execute Tests

```bash
mvn test
```

## Assumptions

* The standard EMI formula is used for all supported loan types.
* Loan tenure is restricted to a maximum of 40 years.
* Annual interest rate must be between 0.01% and 100%.

## Author

Deepak Kasgar
