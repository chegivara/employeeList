package employee.list.client.model;


import com.google.gwt.user.client.Window;
import employee.list.common.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple model handler
 * manage todo list
 * 
 * @author AGI
 *
 */
public class ModelHandler {

	public List<Employee> employeeList;
	
	public ModelHandler(){
		employeeList = new ArrayList<>();
	}

	public void add(Employee t) {
		employeeList.add(t);
	}

	public void add(List<Employee> t) {

		employeeList.addAll(t);
	}


	public void remove(Employee t) {
		employeeList.remove(t);
	}

	public void removeAll() {
		employeeList.clear();
	}

	public List<Employee> getAll() {
		return employeeList;
	}

	public void reloadAll(List<Employee> list) {
		employeeList.clear();
		for(Employee t : list){
			add(t);
		}
	}
	
}
