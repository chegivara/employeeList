package employee.list.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import employee.list.client.event.*;
import employee.list.client.json.JsonHelper;
import employee.list.client.model.ModelHandler;
import employee.list.client.ui.MainPanel;
import employee.list.common.model.Employee;


import java.util.Date;
import java.util.List;

/**
 * Web App Controller Manage all business events and communicate with server
 * services
 */
public class WebAppController {

    /**
     * Event Bus
     */
    private SimpleEventBus _eventBus;

    /**
     * Model Handler
     */
    private ModelHandler _modelHandler;

    /**
     * main panel UI
     */
    private MainPanel _mainPanel;

    @Inject
    public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {
        _eventBus = eventBus;
        _modelHandler = modelHandler;
        _mainPanel = mainPanel;

    }

    /**
     * Bind all events handler
     */
    public void bindHandlers() {
        _eventBus.addHandler(EditEmployeeEvent.TYPE, event -> EditEmployee(event.getEmployeeId(), event.getEmployeeFirstName(), event.getEmployeeLastName(), event.getEmployeeBirthDate()));
        _eventBus.addHandler(AddAllEmployeeEvent.TYPE, event -> updateAllEmployees());
        _eventBus.addHandler(AddEmployeeEvent.TYPE, event -> addEmployee(event.getEmployeeFirstName(), event.getEmployeeLastName(), event.getEmployeeBirthDate()));
        _eventBus.addHandler(DeleteEmployeeEvent.TYPE, event -> deleteEmployee(event.getEmployee()));
        _eventBus.addHandler(LoadEvent.TYPE, event -> loadEmployeeList());
    }

    public  void EditEmployee(Integer employeeId, String employeeFirstName, String employeeLastName, Date employeeBirthDate) {

        String pageBaseUrl = GWT.getHostPageBaseURL();
        // String baseUrl = GWT.getModuleBaseURL();

        JSONObject element = new JSONObject ();
        element.put("firstName",new JSONString(employeeFirstName));
        element.put("lastName",new JSONString(employeeLastName));
        element.put("birthDate",new JSONString(DateTimeFormat.getFormat("yyyy-MM-dd").format(employeeBirthDate)));
        element.put("id",new JSONNumber(employeeId));
        RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, pageBaseUrl+"rest/employee/update" );
        rb.setHeader("Content-Type", "application/json");
        try {
            rb.sendRequest(element.toString(),
                    new RequestCallback() {

                        public void onError(Request request, Throwable e) {
                            Window.alert("error = " + e.getMessage());
                        }

                        public void onResponseReceived(Request request, Response response) {
                            if (200 == response.getStatusCode()) {
                                _mainPanel.refreshTable();
                            }
                        }
                    });
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }

    }

    public void loadEmployeeList() {
        String pageBaseUrl = GWT.getHostPageBaseURL();
        // String baseUrl = GWT.getModuleBaseURL();
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/employee");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String text = response.getText();
                    List<Employee> employeeListList = JsonHelper.parseDataList(text);

                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    } public void removeEmployee(Employee employee) {
        String pageBaseUrl = GWT.getHostPageBaseURL();
        // String baseUrl = GWT.getModuleBaseURL();
        RequestBuilder rb = new RequestBuilder(RequestBuilder.DELETE, pageBaseUrl + "/rest/employee?id="+employee.getId());
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    _mainPanel.refreshTable();
                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    }
    public void addEmployee(Employee employee) {
        String pageBaseUrl = GWT.getHostPageBaseURL();
        // String baseUrl = GWT.getModuleBaseURL();

        JSONObject element = new JSONObject ();
        element.put("firstName",new JSONString(employee.getFirstName()));
        element.put("lastName",new JSONString(employee.getLastName()));
        element.put("birthDate",new JSONString(DateTimeFormat.getFormat("yyyy-MM-dd").format(employee.getBirthDate())));
        element.put("id",new JSONObject(null));
        RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, pageBaseUrl+"rest/employee" );
        rb.setHeader("Content-Type", "application/json");
        try {
            rb.sendRequest(element.toString(),
                    new RequestCallback() {

                        public void onError(Request request, Throwable e) {
                            Window.alert("error = " + e.getMessage());
                        }

                        public void onResponseReceived(Request request, Response response) {
                            if (200 == response.getStatusCode()) {
                                _mainPanel.refreshTable();
                            }
                        }
                    });
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    }


    protected void deleteEmployee(Employee employee) {
        removeEmployee(employee);
        _mainPanel.refreshTable();
    }
    protected void updateAllEmployees() {
        loadEmployeeList();
    }
    /**
     * create and add a Employee with given name and birth date
     *
     * @param firstname
     * @param lastname
     * @param birthDate
     */
    protected void addEmployee(String firstname, String lastname, Date birthDate) {
        Employee t = Employee
                .newBuilder()
                .setBirthDate(birthDate)
                .setFirstName(firstname)
                .setLastName(lastname)
                .build();
        addEmployee(t);

    }
}
