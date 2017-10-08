package employee.list.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import employee.list.client.controller.WebAppController;
import employee.list.client.resource.ApplicationResources;
import employee.list.client.ui.MainPanel;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
public class GwtWebApp implements EntryPoint {
    private final GwtWebAppGinjector injector = GWT.create(GwtWebAppGinjector.class);
    @Override
    public void onModuleLoad() {
// ensure resources are injected
        ApplicationResources.INSTANCE.style().ensureInjected();
        // get controler from gin jector
        WebAppController controller = injector.getWebAppController();
        // bind event handlers
        controller.bindHandlers();
        // get main panel
        MainPanel mainPanel = injector.getMainPanel();
        // add for display
        RootLayoutPanel.get().add(mainPanel);
    }
}
