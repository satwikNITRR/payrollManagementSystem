package com.satwik.payrollManagement.service;

import com.satwik.payrollManagement.Payroll;
import com.satwik.payrollManagement.dto.PayrollRequestDto;
import com.satwik.payrollManagement.dto.PayrollResponseDto;
import com.satwik.payrollManagement.exception.ResourceNotFoundException;
import com.satwik.payrollManagement.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public PayrollResponseDto createEmployeePayroll(PayrollRequestDto payrollRequestDto) {
        Payroll payroll = mapToEntity(payrollRequestDto);
        Payroll savedPayroll = payrollRepository.save(payroll);
        return mapToResponseDto(savedPayroll);
    }

    public List<PayrollResponseDto> getAllPayrolls() {
        return payrollRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public PayrollResponseDto getPayrollById(Integer id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id: " + id));
        return mapToResponseDto(payroll);
    }

    public List<PayrollResponseDto> getPayrollsByEmployeeId(Integer employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public PayrollResponseDto updatePayroll(Integer id, PayrollRequestDto payrollRequestDto) {
        Payroll existingPayroll = payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id: " + id));

        existingPayroll.setEmployeeId(payrollRequestDto.getEmployeeId());
        existingPayroll.setSalary(payrollRequestDto.getSalary());
        existingPayroll.setCurrency(payrollRequestDto.getCurrency());
        existingPayroll.setStatus(payrollRequestDto.getStatus());

        Payroll updatedPayroll = payrollRepository.save(existingPayroll);
        return mapToResponseDto(updatedPayroll);
    }

    public void deletePayroll(Integer id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id: " + id));
        payrollRepository.delete(payroll);
    }

    private Payroll mapToEntity(PayrollRequestDto dto) {
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(dto.getEmployeeId());
        payroll.setSalary(dto.getSalary());
        payroll.setCurrency(dto.getCurrency());
        payroll.setStatus(dto.getStatus());
        return payroll;
    }

    private PayrollResponseDto mapToResponseDto(Payroll payroll) {
        PayrollResponseDto responseDto = new PayrollResponseDto();
        responseDto.setId(payroll.getId());
        responseDto.setEmployeeId(payroll.getEmployeeId());
        responseDto.setSalary(payroll.getSalary());
        responseDto.setCurrency(payroll.getCurrency());
        responseDto.setStatus(payroll.getStatus());
        return responseDto;
    }
}
