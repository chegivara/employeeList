package employee.list.server.web;

import employee.list.common.SqlEnum;
import employee.list.common.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.*;
import java.util.List;

/**
 * Created by m.kuznecov on 03.10.2017.
 */
public class EmployeeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private  RowMapper <Employee> rm =  (ResultSet result,int rowNum) -> {
        Employee employee = new Employee();
        employee.setId(result.getInt("Id"));
        employee.setFirstName(result.getString("firstname"));
        employee.setLastName(result.getString("lastname"));
        employee.setBirthDate(result.getDate("birthdate"));
        return employee;
    };

    public List <Employee> getAll(){
        return jdbcTemplate.query(SqlEnum.SELECTALL.toString(), rm);
    }
    public Employee addEmployee(Employee employee){
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SqlEnum.INSERT.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                System.out.println(employee.getBirthDate());
                ps.setDate(3,  new java.sql.Date(employee.getBirthDate().getTime()));
                return ps;
            }
        }, holder);
        return getBiId(Integer.valueOf(holder.getKeys().get("id").toString()));
    }
    public Employee updateEmployee(Employee employee){
         jdbcTemplate.update(SqlEnum.UPDATE.toString(), employee.getFirstName(),employee.getLastName(),employee.getBirthDate(),employee.getId());
         return getBiId(employee.getId());
    }



    public Employee getBiId(Integer id){
       return jdbcTemplate.queryForObject(
                SqlEnum.SELECTBIID.toString(),
                new Object[]{id}, rm);

    }
    public void deleteBiId(Integer id){
        jdbcTemplate.update(SqlEnum.DELETEBYID.toString(), id);
    }
}
