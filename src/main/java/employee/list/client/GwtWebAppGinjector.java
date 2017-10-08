package employee.list.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import employee.list.client.controller.WebAppController;
import employee.list.client.model.ModelHandler;
import employee.list.client.resource.ApplicationResources;
import employee.list.client.resource.Messages;
import employee.list.client.ui.MainPanel;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
@GinModules(GwtWebAppGinModule.class)
public interface GwtWebAppGinjector extends Ginjector {
    public SimpleEventBus getEventBus();

    public ApplicationResources getApplicationResources();

    public Messages getMessages();

    public WebAppController getWebAppController();

    public ModelHandler getModelHandler();

    public MainPanel getMainPanel();
}
