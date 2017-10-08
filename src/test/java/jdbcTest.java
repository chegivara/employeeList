import employee.list.common.model.Employee;
import employee.list.server.web.EmployeeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by m.kuznecov on 04.10.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("/TestDataSource.xml")
public class jdbcTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployeeDAO employeeDAO;
    @Test
    public void insert() {
        employeeDAO.addEmployee(Employee.newBuilder().setBirthDate(new Date()).setFirstName("Кузнецов").setLastName("Максим").build());
    }

    @Test
    public void select() {
        employeeDAO.getAll();
        System.out.println(employeeDAO.getAll());
    }

    @Test
    public void delete() {
        employeeDAO.deleteBiId(2);
    }
    @Test
    public void selectAll() {
        System.out.println(employeeDAO.getAll());
    }

}