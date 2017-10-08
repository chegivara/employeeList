package employee.list.client.json;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

public class JsEmployee extends JavaScriptObject {

	protected JsEmployee() {
	}

	public native final String id() /*-{
		return this.id;
	}-*/;
	public native final String firstName() /*-{
        return this.firstName;
    }-*/;
	public native final String lastName() /*-{
        return this.lastName;
    }-*/;
	public native final Date birthDate() /*-{
        return this.birthDate;
    }-*/;


}
