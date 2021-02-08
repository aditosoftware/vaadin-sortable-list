package de.aditosoftware.vaadin.addon.client.adapter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import de.aditosoftware.vaadin.addon.client.shared.SortableOptions;

public class SortableAdapter
{
  public static native JavaScriptObject configureSortable(Element pContainerElement, SortableOptions pOptions) /*-{
      var sortable = $wnd.Sortable;

      console.log(pOptions);
      console.log(pOptions.@de.aditosoftware.vaadin.addon.client.shared.SortableOptions::getDraggable()());

      return new sortable["default"](pContainerElement, {
          draggable: pOptions.@de.aditosoftware.vaadin.addon.client.shared.SortableOptions::getDraggable()(),
          handle: pOptions.@de.aditosoftware.vaadin.addon.client.shared.SortableOptions::getHandle()(),
          mirror: {
              xAxis: pOptions.@de.aditosoftware.vaadin.addon.client.shared.SortableOptions::isMirrorXAxis()(),
              yAxis: pOptions.@de.aditosoftware.vaadin.addon.client.shared.SortableOptions::isMirrorYAxis()(),
          },
      });
  }-*/;
}
