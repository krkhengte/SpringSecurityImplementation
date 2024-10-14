package com.example.springsecurity.repo;

import com.example.springsecurity.model.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeHistoryRepo extends JpaRepository<EmployeeHistory,Long> {

    @Query("SELECT eh FROM EmployeeHistory eh WHERE eh.employee.id = :id")
    public List<EmployeeHistory> getEmployeeHistory(@Param("id") Long id);


}
