package employee.list.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

/**
 * Resource bundle
 * Gather images & css
 * @author AGI
 *
 */
public interface ApplicationResources extends ClientBundle {
    public static final ApplicationResources INSTANCE = GWT.create(ApplicationResources.class);
    
    @Source("GwtWebAppStyles.css")
    public GwtWebAppStyles style();
    
    @Source("delete.png")
    @ImageOptions(repeatStyle=RepeatStyle.Both)
    ImageResource deleteIcon();
    
    @Source("add.png")
    @ImageOptions(repeatStyle=RepeatStyle.Both)
    ImageResource addIcon();

    @Source("edit.png")
    @ImageOptions(repeatStyle=RepeatStyle.Both)
    ImageResource editIcon();

    @Source("delete.png")
    @ImageOptions(repeatStyle=RepeatStyle.Both)
    ImageResource clearIcon();
    

}
