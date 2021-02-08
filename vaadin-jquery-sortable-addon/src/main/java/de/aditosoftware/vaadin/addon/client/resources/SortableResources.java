package de.aditosoftware.vaadin.addon.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.*;

public interface SortableResources extends ClientBundle
{
  SortableResources INSTANCE = GWT.create(SortableResources.class);

  @Source("draggable.js")
  TextResource sortableCore();
}
