package com.neosuniversity.springjdbcdemonov.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.springjdbcdemonov.domain.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
