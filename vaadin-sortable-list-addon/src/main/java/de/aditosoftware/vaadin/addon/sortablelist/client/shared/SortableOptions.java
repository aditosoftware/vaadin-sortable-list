package de.aditosoftware.vaadin.addon.sortablelist.client.shared;

import java.io.Serializable;

public class SortableOptions implements Serializable
{
  // Base properties.
  public String draggable;
  public String handle;

  // Mirror plugin.
  public boolean mirrorXAxis;
  public boolean mirrorYAxis;
  public boolean mirrorConstrainDimensions;

  public SortableOptions()
  {
  }

  public SortableOptions(String pDraggable, String pHandle, boolean pMirrorXAxis, boolean pMirrorYAxis, boolean pMirrorConstrainDimensions)
  {
    draggable = pDraggable;
    handle = pHandle;
    mirrorXAxis = pMirrorXAxis;
    mirrorYAxis = pMirrorYAxis;
    mirrorConstrainDimensions = pMirrorConstrainDimensions;
  }

  public String getDraggable()
  {
    return draggable;
  }

  public String getHandle()
  {
    return handle;
  }

  public boolean isMirrorXAxis()
  {
    return mirrorXAxis;
  }

  public boolean isMirrorYAxis()
  {
    return mirrorYAxis;
  }

  public boolean isMirrorConstrainDimensions()
  {
    return mirrorConstrainDimensions;
  }
}
