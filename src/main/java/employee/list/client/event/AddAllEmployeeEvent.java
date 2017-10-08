package employee.list.client.event;

import com.google.gwt.event.shared.GwtEvent;
import employee.list.common.model.Employee;

/**
 * Created by m.kuznecov on 05.10.2017.
 */
public class AddAllEmployeeEvent extends GwtEvent<AddAllEmployeeEventHandler> {
    public static Type<AddAllEmployeeEventHandler> TYPE = new Type<AddAllEmployeeEventHandler>();
    Employee employee;

    public AddAllEmployeeEvent() {
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public Type<AddAllEmployeeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddAllEmployeeEventHandler handler) {
        handler.onAddAllEmployeeEventHandler(this);

    }
}
