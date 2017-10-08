package employee.list.client.event;

import com.google.gwt.event.shared.GwtEvent;
import employee.list.common.model.Employee;

import java.util.Date;

public class EditEmployeeEvent extends GwtEvent<EditEmployeeEventHandler> {
    public static Type<EditEmployeeEventHandler> TYPE = new Type<EditEmployeeEventHandler>();
    Employee employee;
    private String _employeeFirstName;
    private String _employeeLastName;
    private Date _employeeBirthDate;
    private Integer _employeeid;


    public String getEmployeeLastName() {
        return _employeeLastName;
    }

    public Date getEmployeeBirthDate() {
        return _employeeBirthDate;
    }

    public String getEmployeeFirstName() {
        return _employeeFirstName;
    }
    public Integer getEmployeeId() {
        return _employeeid;
    }
    public EditEmployeeEvent(
            Integer employeeid,String employeeFirstName,
                            String employeeLastName,
                            Date employeeBirthDate) {
        _employeeFirstName = employeeFirstName;
        _employeeLastName = employeeLastName;
        _employeeBirthDate = employeeBirthDate;
        _employeeid=employeeid;
    }


    public Employee getEmployee() {
        return employee;
    }

    @Override
    public Type<EditEmployeeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditEmployeeEventHandler handler) {
        handler.onEditEmployeeEventHandler(this);

    }
}
