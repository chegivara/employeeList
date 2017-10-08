package employee.list.client.event;

import com.google.gwt.event.shared.GwtEvent;

import java.util.Date;


public class AddEmployeeEvent extends GwtEvent<AddEmployeeEventHandler> {

	public static Type<AddEmployeeEventHandler> TYPE = new Type<AddEmployeeEventHandler>();

	private String _employeeFirstName;
	private String _employeeLastName;
	private Date _employeeBirthDate;



	public String getEmployeeLastName() {
		return _employeeLastName;
	}

	public Date getEmployeeBirthDate() {
		return _employeeBirthDate;
	}

	public String getEmployeeFirstName() {
		return _employeeFirstName;
	}

	public AddEmployeeEvent(String employeeFirstName,
							String employeeLastName,
							Date employeeBirthDate) {
		_employeeFirstName = employeeFirstName;
		_employeeLastName = employeeLastName;
		_employeeBirthDate = employeeBirthDate;
	}

	@Override
	protected void dispatch(AddEmployeeEventHandler handler) {
		handler.onAddEmployeeEventHandler(this);
	}

	@Override
	public Type<AddEmployeeEventHandler> getAssociatedType() {
		return TYPE;
	}

}
