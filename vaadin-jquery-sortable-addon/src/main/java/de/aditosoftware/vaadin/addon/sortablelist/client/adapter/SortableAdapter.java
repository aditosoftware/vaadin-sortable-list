package de.aditosoftware.vaadin.addon.sortablelist.client.adapter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;

public class SortableAdapter
{
  public static native JavaScriptObject configureSortable(Element pContainerElement, SortableOptions pOptions) /*-{
      var sortable = $wnd.Sortable;

      var constraintDimensions = false;
      if (pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::isMirrorConstrainDimensions()())
      {
          constraintDimensions = pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::isMirrorConstrainDimensions()();
      }

      return new sortable["default"](pContainerElement, {
          draggable: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::getDraggable()(),
          handle: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::getHandle()(),
          mirror: {
              xAxis: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::isMirrorXAxis()(),
              yAxis: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions::isMirrorYAxis()(),
              constrainDimensions: constraintDimensions,
          },
      });
  }-*/;
}
