package employee.list.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import employee.list.client.controller.WebAppController;
import employee.list.client.model.ModelHandler;
import employee.list.client.resource.ApplicationResources;
import employee.list.client.resource.Messages;
import employee.list.client.ui.MainPanel;

/**
 * Created by m.kuznecov on 02.10.2017.
 */
public class GwtWebAppGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
// Resources
        bind(Messages.class).in(Singleton.class);
        bind(ApplicationResources.class).in(Singleton.class);

        // Core
        bind(SimpleEventBus.class).in(Singleton.class);
        bind(WebAppController.class).in(Singleton.class);
        bind(ModelHandler.class).in(Singleton.class);

        // UI
        bind(MainPanel.class).in(Singleton.class);
    }
}
