package de.aditosoftware.vaadin.addon.sortablelist.events;

import com.vaadin.event.ConnectorEventListener;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface SortableListEvents {
  interface SortListener extends ConnectorEventListener {
    Method onSortMethod =
        ReflectTools.findMethod(
            SortableListEvents.SortListener.class, "onSort", SortableListEvents.SortEvent.class);

    void onSort(SortEvent sortEvent);
  }

  interface SortNotifier extends Serializable {
    /**
     * Will add a listener which will be called when the user sorts an item within the list.
     *
     * @param listener The listener which will be called on sort.
     * @return The registration for the listener.
     */
    Registration addSortListener(SortListener listener);
  }

  /**
   * Event which describes the sort of a component. This includes the old and new index within the
   * list.
   */
  class SortEvent extends Component.Event {
    private final Component sortedComponent;
    private final int oldIndex;
    private final int newIndex;

    /**
     * Constructs a new event with the specified source component.
     *
     * @param source The source component of this event.
     * @param sortedComponent The component which has been sorted.
     * @param oldIndex The old index within the list of the sorted component.
     * @param newIndex The new index within the list of the sorted component.
     */
    public SortEvent(Component source, Component sortedComponent, int oldIndex, int newIndex) {
      super(source);
      this.sortedComponent = sortedComponent;
      this.oldIndex = oldIndex;
      this.newIndex = newIndex;
    }

    /** Will return the component which has been sorted. */
    public Component getSortedComponent() {
      return sortedComponent;
    }

    /** Will return the old index within the list of the sorted component. */
    public int getOldIndex() {
      return oldIndex;
    }

    /** Will return the new index within the list of the sorted component. */
    public int getNewIndex() {
      return newIndex;
    }
  }
}
