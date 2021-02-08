package de.aditosoftware.vaadin.addon.configuration;

import de.aditosoftware.vaadin.addon.client.shared.SortableOptions;

public class SortableOptionsBuilder
{
  // Base properties.
  String draggable;
  String handle;

  // Mirror plugin.
  boolean mirrorXAxis;
  boolean mirrorYAxis;

  public SortableOptionsBuilder setDraggable(String pDraggable)
  {
    draggable = pDraggable;
    return this;
  }

  public SortableOptionsBuilder setHandle(String pHandle)
  {
    handle = pHandle;
    return this;
  }

  public SortableOptionsBuilder setMirrorXAxis(boolean pMirrorXAxis)
  {
    mirrorXAxis = pMirrorXAxis;
    return this;
  }

  public SortableOptionsBuilder setMirrorYAxis(boolean pMirrorYAxis)
  {
    mirrorYAxis = pMirrorYAxis;
    return this;
  }

  public SortableOptions build()
  {
    return new SortableOptions(draggable, handle, mirrorXAxis, mirrorYAxis);
  }
}
