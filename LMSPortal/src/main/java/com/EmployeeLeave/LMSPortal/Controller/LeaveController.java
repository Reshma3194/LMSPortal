package com.EmployeeLeave.LMSPortal.Controller;

import com.EmployeeLeave.LMSPortal.model.Leave;
import com.EmployeeLeave.LMSPortal.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    
    @Autowired
    private LeaveService leaveService;
    
    @PostMapping("/apply/{empId}")
    public ResponseEntity<Leave> applyLeave(
            @PathVariable Integer empId,
            @RequestBody Leave leave) {
        Leave appliedLeave = leaveService.applyLeave(empId, leave);
        return new ResponseEntity<>(appliedLeave, HttpStatus.CREATED);
    }
    
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Leave>> getEmployeeLeaves(@PathVariable Integer empId) {
        List<Leave> leaves = leaveService.getEmployeeLeaves(empId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        List<Leave> pendingLeaves = leaveService.getPendingLeaves();
        return new ResponseEntity<>(pendingLeaves, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        List<Leave> leaves = leaveService.getAllLeaves();
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<Leave> approveLeave(@PathVariable Integer leaveId) {
        Leave approvedLeave = leaveService.approveLeave(leaveId);
        return new ResponseEntity<>(approvedLeave, HttpStatus.OK);
    }
    
    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<Leave> rejectLeave(@PathVariable Integer leaveId) {
        Leave rejectedLeave = leaveService.rejectLeave(leaveId);
        return new ResponseEntity<>(rejectedLeave, HttpStatus.OK);
    }
}