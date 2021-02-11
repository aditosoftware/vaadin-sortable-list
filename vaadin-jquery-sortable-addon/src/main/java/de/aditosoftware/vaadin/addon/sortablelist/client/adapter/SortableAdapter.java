package de.aditosoftware.vaadin.addon.sortablelist.client.adapter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;

import java.util.function.Consumer;

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

  public static native void addSortableSortListener (JavaScriptObject pSortableInstance, Consumer<Object> pOnSort) /*-{
      pSortableInstance.on('sortable:stop', function (event) {
          console.log(event);
          pOnSort.@java.util.function.Consumer::accept(*)(event);
      });
  }-*/;
}
