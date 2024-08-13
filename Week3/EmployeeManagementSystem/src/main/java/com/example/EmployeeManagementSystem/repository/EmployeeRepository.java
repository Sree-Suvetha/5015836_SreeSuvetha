package com.example.EmployeeManagementSystem.repository;

import java.awt.print.Pageable;
import java.util.*;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.EmployeeManagementSystem.Employee;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(
        name = "Employee.findByName",
        query = "SELECT e FROM Employee e WHERE e.name = :name"
    )
})

public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{
	List<Employee> findByDepartmentName(String name);

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Employee findEmployeeByEmail(String email);

    List<Employee> findByName(@Param("name") String name);
    
    Page findAll(Pageable pageable);
    
    EmployeeDTO findEmployeeDTOByEmail(String email);
	
}


