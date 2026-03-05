package com.satwik.payrollManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PayrollRequestDto {

    @NotNull(message = "employeeId is required")
    @Positive(message = "employeeId must be positive")
    private Integer employeeId;

    @NotNull(message = "salary is required")
    @Positive(message = "salary must be positive")
    private Double salary;

    @NotBlank(message = "currency is required")
    @Size(min = 3, max = 10, message = "currency must be between 3 and 10 characters")
    private String currency;

    @NotBlank(message = "status is required")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "status must be ACTIVE or INACTIVE")
    private String status;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
