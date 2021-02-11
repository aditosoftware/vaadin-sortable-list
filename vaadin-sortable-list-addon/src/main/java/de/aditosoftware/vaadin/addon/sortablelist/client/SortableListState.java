package de.aditosoftware.vaadin.addon.sortablelist.client;

import com.vaadin.shared.ui.AbstractLayoutState;
import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;

public class SortableListState extends AbstractLayoutState
{
  public String draggable;
  public String handle;

  public boolean mirrorXAxis;
  public boolean mirrorYAxis;
  public boolean mirrorConstrainDimensions;
}
