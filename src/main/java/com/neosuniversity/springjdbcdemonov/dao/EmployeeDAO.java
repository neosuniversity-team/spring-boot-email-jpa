package com.neosuniversity.springjdbcdemonov.dao;

import com.neosuniversity.springjdbcdemonov.domain.Employee;

import java.util.List;

/**
 * The interface Employee dao.
 */
public interface EmployeeDAO {

    /**
     * Save.
     *
     * @param employee the employee
     */
    public void save (Employee employee);

    public List<Employee> findAll();

    public void update(Employee employee);

    public void delete(Integer id);
}
