package de.aditosoftware.vaadin.addon.sortablelist.client.adapter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState;
import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;

import java.util.function.Consumer;

public class SortableAdapter {
    public static native JavaScriptObject configureSortable(Element pContainerElement, SortableListState pOptions) /*-{
        var sortable = $wnd.Sortable;

        var constraintDimensions = false;
        if (pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorConstrainDimensions) {
            constraintDimensions = pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorConstrainDimensions;
        }

        return new sortable["default"](pContainerElement, {
            draggable: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::draggable,
            handle: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::handle,
            mirror: {
                xAxis: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorXAxis,
                yAxis: pOptions.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorYAxis,
                constrainDimensions: constraintDimensions
            },
        });
    }-*/;

    public static native void addSortableSortListener(JavaScriptObject pSortableInstance, Consumer<SortableAdapterStopEvent> pOnSort) /*-{
        pSortableInstance.on('sortable:stop', function (event) {
            var transformedEvent = @de.aditosoftware.vaadin.addon.sortablelist.client.adapter.SortableAdapterStopEvent::new(II)(event.data.oldIndex, event.data.newIndex)

            pOnSort.@java.util.function.Consumer::accept(*)(transformedEvent);
        });
    }-*/;
}
