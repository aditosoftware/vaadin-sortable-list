package de.aditosoftware.vaadin.addon.client.adapter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class SortableAdapter
{
  public static native JavaScriptObject configureSortable(Element pContainerElement) /*-{
    var sortable = $wnd.Sortable;

    return new sortable["default"](pContainerElement, {
        draggable: ".list-bar",
        mirror: {
            xAxis: false,
        },
    });
  }-*/;
}
