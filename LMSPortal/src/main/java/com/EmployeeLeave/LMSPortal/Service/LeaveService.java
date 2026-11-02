
package com.EmployeeLeave.LMSPortal.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeLeave.LMSPortal.model.Employee;
import com.EmployeeLeave.LMSPortal.model.Leave;
import com.EmployeeLeave.LMSPortal.repository.EmployeeRepository;
import com.EmployeeLeave.LMSPortal.repository.LeaveRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Apply for leave
    public Leave applyLeave(Integer empId, Leave leave) {

        // Validate dates
        if (leave.getStartDate() == null || leave.getEndDate() == null) {
            throw new IllegalArgumentException(
                "Start date and end date are required"
            );
        }

        if (leave.getStartDate().isAfter(leave.getEndDate())) {
            throw new IllegalArgumentException(
                "Start date cannot be after end date"
            );
        }

        if (leave.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Cannot apply for past dates"
            );
        }

        // Find employee
        Employee employee = employeeRepository.findById(empId)
            .orElseThrow(() ->
                new RuntimeException("Employee not found with ID: " + empId)
            );

        // Associate leave with employee
        leave.setEmployee(employee);

        // Set initial status
        leave.setStatus("PENDING");

        // Save leave
        return leaveRepository.save(leave);
    }

    // Get leaves for a particular employee
    public List<Leave> getEmployeeLeaves(Integer empId) {

        Employee employee = employeeRepository.findById(empId)
            .orElseThrow(() ->
                new RuntimeException("Employee not found with ID: " + empId)
            );

        return leaveRepository.findByEmployeeEmpID(empId);
    }

    // Get all pending leaves
    public List<Leave> getPendingLeaves() {
        return leaveRepository.findByStatus("PENDING");
    }

    // Get all leave records
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // Approve a leave request
    public Leave approveLeave(Integer leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
            .orElseThrow(() ->
                new RuntimeException("Leave not found with ID: " + leaveId)
            );

        leave.setStatus("APPROVED");

        return leaveRepository.save(leave);
    }

    // Reject a leave request
    public Leave rejectLeave(Integer leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
            .orElseThrow(() ->
                new RuntimeException("Leave not found with ID: " + leaveId)
            );

        leave.setStatus("REJECTED");

        return leaveRepository.save(leave);
    }
}
