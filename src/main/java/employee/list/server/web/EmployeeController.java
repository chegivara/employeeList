package employee.list.server.web;

import employee.list.common.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {
    @Autowired(required = true)
    private EmployeeDAO employeeDAO;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> all() {
        return employeeDAO.getAll();
    }

    @RequestMapping(value = "byid",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Employee getBiId(@RequestParam Integer id) {
        return employeeDAO.getBiId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Employee put(@RequestBody Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Integer id) {
        employeeDAO.deleteBiId(id);
    }

}

