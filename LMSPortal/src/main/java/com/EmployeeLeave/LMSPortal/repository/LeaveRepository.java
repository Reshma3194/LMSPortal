package com.EmployeeLeave.LMSPortal.repository;
import com.EmployeeLeave.LMSPortal.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployeeEmpID(Integer empId);

    List<Leave> findByStatus(String status);
}
