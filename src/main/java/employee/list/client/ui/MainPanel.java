package employee.list.client.ui;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import employee.list.client.event.AddAllEmployeeEvent;
import employee.list.client.event.AddEmployeeEvent;
import employee.list.client.event.DeleteEmployeeEvent;
import employee.list.client.event.EditEmployeeEvent;
import employee.list.client.json.JsonHelper;
import employee.list.client.model.ModelHandler;
import employee.list.client.ui.component.ImageButton;
import employee.list.common.model.Employee;

import java.util.*;
import java.util.function.Supplier;

import static com.google.gwt.user.client.Window.alert;

/**
 * Main UI component
 *
 * @author AGI
 */
public class MainPanel extends Composite {

    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);

    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
    }

	/*
     * UI components
	 */

    @UiField
    ImageButton addButton;

    @UiField
    ImageButton deleteButton;

    @UiField
    HTMLPanel mainPanel;
    ListDataProvider<Employee> dataProvider;
    @UiField(provided = true)
    DataGrid<Employee> dataGrid;
    final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();

    /**
     * event bus
     */
    private SimpleEventBus _eventBus;

    /**
     * model
     */
    private ModelHandler _modelHandler;

    @Inject
    public MainPanel(SimpleEventBus eventBus, ModelHandler modelHandler) {

        _modelHandler = modelHandler;
        _eventBus = eventBus;
        createTable();
        initWidget(uiBinder.createAndBindUi(this));
        mainPanel.getElement().setId("mainPanel");
        mainPanel.add(dataGrid);
    }

    public void updateTableData(List<Employee> employees) {
        dataGrid.setRowCount(employees.size(), true);
        dataGrid.setRowData(0, employees);
        dataGrid.setVisible(true);
    }

    @UiHandler("addButton")
    void onAddButtonClick(ClickEvent e) {
        PopUp popUp = new PopUp(null);
        popUp.show();

    }
    @UiHandler("editButton")
    void onEditButtonClick(ClickEvent e) {
        if (selectionModel.getSelectedSet().size() != 0)
        {
            Employee employee=selectionModel.getSelectedSet().iterator().next();
            PopUp  popUp = new PopUp(employee);
        popUp.show();}
        else {
            alert("Сотрудник не выбран");
        }


    }
    @UiHandler("deleteButton")
    void onClearButtonClick(ClickEvent e) {
        if (selectionModel.getSelectedSet().size() != 0)
            _eventBus.fireEvent(new DeleteEmployeeEvent(selectionModel.getSelectedSet().iterator().next()));
        else {
            alert("Сотрудник не выбран");
        }
    }

    private void createTable() {
        dataGrid = new DataGrid<Employee>(Integer.MAX_VALUE);
        dataGrid.setSelectionModel(selectionModel);
        TextColumn<Employee> id = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getId().toString();
            }
        };

        dataGrid.addColumn(id, "id");
        dataGrid.setColumnWidth(id, "0px");
        TextColumn<Employee> firstName = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getFirstName();
            }
        };
        dataGrid.addColumn(firstName, "Имя");
        firstName.setFieldUpdater(new FieldUpdater<Employee, String>() {
            @Override
            public void update(int index, Employee object, String value) {
                alert("You changed the name of " + object.getFirstName() + " to " + value);
                object.setFirstName(value);

            }
        });
        TextColumn<Employee> lastName = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getLastName();
            }
        };
        lastName.setFieldUpdater(new FieldUpdater<Employee, String>() {

            @Override
            public void update(int index, Employee object, String value) {

                object.setLastName(value);
                dataGrid.redraw();
            }
        });
        dataGrid.addColumn(lastName, "Фамилия");

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);
        DatePickerCell datePickerCellnew = new DatePickerCell(dateTimeFormat);
        Column<Employee, Date> dateColumn = new Column<Employee, Date>(
                new DateCell(
                        DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT))) {
            @Override
            public Date getValue(Employee object) {
                return dateTimeFormat.parse(String.valueOf(object.getBirthDate()));

            }
        };

        dataGrid.addColumn(dateColumn, "Дата рождения");
        String pageBaseUrl = GWT.getHostPageBaseURL();


        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/employee");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String text = response.getText();
                    _modelHandler.add(JsonHelper.parseDataList(text));
                    List<Employee> employeeListList = JsonHelper.parseDataList(text);
                    ListDataProvider<Employee> dataProvider = new ListDataProvider<Employee>();
                    dataProvider.setList(employeeListList);
                    dataProvider.addDataDisplay(dataGrid);
                    dataProvider.refresh();
                    mainPanel.add(dataGrid);
                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            alert("error = " + e.getMessage());
        }
    }


    public void refreshTable(){
        String pageBaseUrl = GWT.getHostPageBaseURL();
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/employee");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String text = response.getText();
                    List<Employee> employeeListList = JsonHelper.parseDataList(text);
                     dataProvider = new ListDataProvider<Employee>();
                    dataProvider.setList(employeeListList);
                    dataGrid.setRowData(employeeListList);
                    dataProvider.refresh();
                    dataGrid.redraw();
                    dataGrid.flush();



                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            alert("error = " + e.getMessage());
        }

        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            alert("error = " + e.getMessage());
        }

    }


    public void removeAllEmployees() {
        dataGrid.getVisibleItems().removeAll(dataGrid.getVisibleItems());
    }

    public void reloadEmployeeList() {
        _eventBus.fireEvent(new AddAllEmployeeEvent());

    }

    class PopUp extends DialogBox {
        Employee employee;
        Button applyButton;
        Button cancelButton;
        DialogBox thisObj;
        PopUp(Employee employee) {
            super();

            String fieldWidth = "150px";
            String fieldHeight = "25px";
            thisObj=this;


            TextBox firstName = new TextBox();
            firstName.setTitle("Имя");
            firstName.setSize(fieldWidth, fieldHeight);
            if(employee!=null)firstName.setValue(employee.getFirstName());


            HorizontalPanel firstNamePanel = new HorizontalPanel();
            firstNamePanel.add( new Label("Имя"));
            firstNamePanel.add(firstName);


            TextBox lastName = new TextBox();
            lastName.setTitle("Фамилия");
            lastName.setSize(fieldWidth, fieldHeight);
            if(employee!=null)lastName.setValue(employee.getLastName());

            HorizontalPanel lastNamePanel = new HorizontalPanel();
            lastNamePanel.add( new Label("Фамилия"));
            lastNamePanel.add(lastName);

            DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);
            DateBox birthDate = new DateBox();
            birthDate.setFormat(new DateBox.DefaultFormat(dateFormat));
            birthDate.setTitle("Дата рождения");
            birthDate.setSize(fieldWidth, fieldHeight);
            if(employee!=null)birthDate.setValue(
                    DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).parse(String.valueOf(employee.getBirthDate()))
            );

            HorizontalPanel birthDatePanel = new HorizontalPanel();
            birthDatePanel.add( new Label("Дата рождения"));
            birthDatePanel.add(birthDate);


            applyButton = new Button(employee != null ? "Изменить" : "Добавить");
            applyButton.addStyleName("btn btn-success");
            applyButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    if (employee != null) {
                        _eventBus.fireEvent(
                                new EditEmployeeEvent(
                                        employee.getId(),
                                        firstName.getValue().toString(),
                                        lastName.getValue().toString(),
                                        birthDate.getValue()));

                    } else {
                        _eventBus.fireEvent(
                                new AddEmployeeEvent(
                                        firstName.getValue().toString(),
                                        lastName.getValue().toString(),
                                        birthDate.getValue()));
                    }
                    thisObj.hide();

                }
            });
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            horizontalPanel.add(applyButton);
            Button cancel =new Button("Отмена", new ClickHandler() {
                public void onClick(ClickEvent event) {
                    thisObj.hide();
                }
            });
            cancel.addStyleName("btn btn-danger");
            horizontalPanel.add(cancel);

            VerticalPanel panel = new VerticalPanel();

            panel.add(lastNamePanel);
            panel.add(firstNamePanel);
            panel.add(birthDatePanel);
            panel.add(horizontalPanel);


            thisObj.setAnimationEnabled(true);
            thisObj.setGlassEnabled(true);
            thisObj.setWidget(panel);
            thisObj.setText("Новый сотрудник");
            thisObj.setPopupPosition(100, 150);
            thisObj.setSize("300px", "300px");

        }

    }
}
