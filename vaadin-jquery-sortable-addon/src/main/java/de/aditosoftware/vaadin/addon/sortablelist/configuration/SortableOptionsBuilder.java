package de.aditosoftware.vaadin.addon.sortablelist.configuration;

import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;

public class SortableOptionsBuilder
{
  // Base properties.
  String draggable;
  String handle;

  // Mirror plugin.
  boolean mirrorXAxis;
  boolean mirrorYAxis;
  boolean mirrorConstrainDimensions;

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

  public SortableOptionsBuilder setMirrorConstrainDimensions(boolean pMirrorConstrainDimensions)
  {
    mirrorConstrainDimensions = pMirrorConstrainDimensions;
    return this;
  }

  public SortableOptions build()
  {
    return new SortableOptions(draggable, handle, mirrorXAxis, mirrorYAxis, mirrorConstrainDimensions);
  }
}
