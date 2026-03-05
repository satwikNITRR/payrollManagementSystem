package com.satwik.payrollManagement.controller;

import com.satwik.payrollManagement.dto.PayrollRequestDto;
import com.satwik.payrollManagement.dto.PayrollResponseDto;
import jakarta.validation.Valid;
import com.satwik.payrollManagement.service.PayrollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PostMapping
    public ResponseEntity<PayrollResponseDto> createPayrollForEmployee(@Valid @RequestBody PayrollRequestDto payrollRequestDto) {
        PayrollResponseDto createdPayroll = payrollService.createEmployeePayroll(payrollRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayroll);
    }

    @GetMapping
    public ResponseEntity<List<PayrollResponseDto>> getAllPayrolls() {
        return ResponseEntity.ok(payrollService.getAllPayrolls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollResponseDto> getPayrollById(@PathVariable Integer id) {
        return ResponseEntity.ok(payrollService.getPayrollById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollResponseDto>> getPayrollsByEmployeeId(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollsByEmployeeId(employeeId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PayrollResponseDto> updatePayroll(@PathVariable Integer id,
                                                            @Valid @RequestBody PayrollRequestDto payrollRequestDto) {
        return ResponseEntity.ok(payrollService.updatePayroll(id, payrollRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Integer id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

}
