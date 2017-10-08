package employee.list.client.ui.schedule;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import employee.list.client.ui.MainPanel;
import employee.list.common.model.Employee;

import java.util.List;

public class ReloadEmployeeListCommand implements ScheduledCommand{

	private List<Employee> employees;
	
	private MainPanel _mainPanel;
	
	private int _index;

	public ReloadEmployeeListCommand(List<Employee> list, MainPanel mainPanel){
		employees = list;
		_mainPanel = mainPanel;
		_index = 0;
	}

	@Override
	public void execute() {

		}
	}


