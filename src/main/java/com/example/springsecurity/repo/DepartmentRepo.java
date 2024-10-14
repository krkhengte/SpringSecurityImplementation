package com.example.springsecurity.repo;

import com.example.springsecurity.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    @Query("SELECT d FROM Department d WHERE d.departmentName = :deptName")
    List<Department> getDepartByDeptName(@Param("deptName") String deptName);

}
