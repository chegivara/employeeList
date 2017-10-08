package employee.list.client.event;

import com.google.gwt.event.shared.GwtEvent;
import employee.list.common.model.Employee;


public class DeleteEmployeeEvent extends GwtEvent<DeleteEmployeeEventHandler> {

	public static Type<DeleteEmployeeEventHandler> TYPE = new Type<DeleteEmployeeEventHandler>();

	Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public DeleteEmployeeEvent(Employee t) {
		this.employee = t;
	}

	@Override
	protected void dispatch(DeleteEmployeeEventHandler handler) {
		handler.onDeleteEmployeeEventHandler(this);
	}

	@Override
	public Type<DeleteEmployeeEventHandler> getAssociatedType() {
		return TYPE;
	}

}
