package com.satwik.payrollManagement.repository;

import com.satwik.payrollManagement.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll,Integer> {
    List<Payroll> findByEmployeeId(Integer employeeId);
}
