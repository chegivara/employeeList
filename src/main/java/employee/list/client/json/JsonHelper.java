package employee.list.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import employee.list.common.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonHelper {

    @SuppressWarnings("unchecked")
    public static List<Employee> parseDataList(String json) {
        List<Employee> arrayList = new ArrayList<>();
        JSONValue jsonVal = JSONParser.parseStrict(json);
        JSONArray object = jsonVal.isArray();

        JsArray<JsEmployee> array = (JsArray<JsEmployee>) object.getJavaScriptObject();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                JsEmployee jsEmployee = array.get(i);
                arrayList.add(Employee.newBuilder()
                        .setId(Integer.valueOf(jsEmployee.id()))
                        .setBirthDate(jsEmployee.birthDate())
                        .setFirstName(jsEmployee.firstName())
                        .setLastName(jsEmployee.lastName())
                        .build());

            }
        }

        return arrayList;
    }

}
