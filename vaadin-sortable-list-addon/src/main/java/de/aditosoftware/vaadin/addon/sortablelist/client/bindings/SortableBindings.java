package de.aditosoftware.vaadin.addon.sortablelist.client.bindings;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState;

import java.util.function.Consumer;

/**
 * Implements the bindings to Sortable. The methods within this class will just be used to access
 * the actual Sortable through jsni.
 */
public class SortableBindings {
  /**
   * Will create a new Sortable instance with the given {@link Element} as container. The {@link
   * SortableListState} is also required to access the configuration properties.
   *
   * @param pContainerElement The container element for which the Sortable instance shall be
   *     created.
   * @param pState The state of the widget for configuration.
   * @return The Sortable instance wrapped in a {@link SortableInstance}.
   */
  public static native SortableInstance createSortable(
      Element pContainerElement, SortableListState pState) /*-{
        var sortable = $wnd.Sortable;

        var constraintDimensions = false;
        if (pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorConstrainDimensions) {
            constraintDimensions = pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorConstrainDimensions;
        }

        // Create the Sortable instance with all properties from the state.
        var sortableInstance = new sortable["default"](pContainerElement, {
            draggable: pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::draggable,
            handle: pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::handle,
            mirror: {
                xAxis: pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorXAxis,
                yAxis: pState.@de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState::mirrorYAxis,
                constrainDimensions: constraintDimensions
            },
        });

        // Create the instance holder and return it.
        return @de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableInstance::new(Lcom/google/gwt/core/client/JavaScriptObject;)(sortableInstance);
    }-*/;

  /**
   * Will add a 'sortable:stop' listener to the given Sortable instance. The caller has to make sure
   * the type of the given object is a Sortable.
   *
   * @param pSortableInstance The Sortable instance as a {@link JavaScriptObject}
   * @param pListener The listener which will be called on sort.
   */
  public static native void addSortableStopListener(
      SortableInstance pSortableInstance, Consumer<SortableBindingStopEvent> pListener) /*-{
        // Return if null or undefined.
        if (!pSortableInstance) {
            return;
        }

        // Register the listener on the Sortable instance.
        pSortableInstance.@de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableInstance::instance.on('sortable:stop', function (event) {
            // Take some properties from the actual event and create an internal one.
            var transformedEvent = @de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableBindingStopEvent::new(II)(event.data.oldIndex, event.data.newIndex)

            // Call the listener consumer with the created internal event.
            pListener.@java.util.function.Consumer::accept(*)(transformedEvent);
        });
    }-*/;

  /**
   * Will destroy the given Sortable instance. The caller has to make sure the type of the given
   * object is a Sortable.
   *
   * @param pSortableInstance The Sortable instance as a {@link JavaScriptObject}.
   */
  public static native void destroySortable(SortableInstance pSortableInstance) /*-{
        // Return if null or undefined.
        if (!pSortableInstance) {
            return
        }

        // Destroy the Sortable instance which will unregister all listeners and clean up.
        pSortableInstance.@de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableInstance::instance.destroy();
    }-*/;
}
