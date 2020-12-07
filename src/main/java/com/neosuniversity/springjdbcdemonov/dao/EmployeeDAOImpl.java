package com.neosuniversity.springjdbcdemonov.dao;

import com.neosuniversity.springjdbcdemonov.domain.Employee;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@CommonsLog
public class EmployeeDAOImpl implements  EmployeeDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void save(Employee employee) {

        String sqlInsert = "INSERT INTO TBL_EMPLOYEES  (first_name, last_name, email) VALUES (?, ? , ?)";

        jdbcTemplate.update(sqlInsert, employee.getFirstName(),
                employee.getLastName(), employee.getEmail());

        log.debug("Se agrego el empleado: " + employee);

    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT id, first_name, last_name, email FROM TBL_EMPLOYEES";

      return  jdbcTemplate.query(sql, (rs, rowNum) -> {
          Employee emp= new Employee();

          emp.setId(rs.getInt("id"));
          emp.setFirstName(rs.getString("first_name"));
          emp.setLastName(rs.getString("last_name"));
          emp.setEmail(rs.getString("email"));

          return emp;
      });

    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE TBL_EMPLOYEES SET first_name = ?, last_name=?, email=? WHERE id = ?  ";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getId());

        log.debug("Se actualizo el empleado " + employee);

    }

    public void delete(Integer id) {
        String sql = "DELETE FROM TBL_EMPLOYEES  WHERE id = ?";

        jdbcTemplate.update(sql, id);

    }


}

/*
class ExtractEmployeeRS implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
         emp.setId(rs.getInt("id"));
         emp.setFirstName(rs.getString("first_name"));
         emp.setLastName(rs.getString("last_name"));
         emp.setEmail(rs.getString("email"));
         return emp;
    }
}
*/