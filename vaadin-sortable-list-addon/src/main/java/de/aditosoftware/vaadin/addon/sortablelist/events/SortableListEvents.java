package de.aditosoftware.vaadin.addon.sortablelist.events;

import com.vaadin.event.ConnectorEventListener;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface SortableListEvents {
    interface SortListener extends ConnectorEventListener {
        Method onSortMethod = ReflectTools.findMethod(
                SortableListEvents.SortListener.class, "onSort",
                SortableListEvents.SortEvent.class);

        void onSort(SortEvent sortEvent);
    }

    interface SortNotifier extends Serializable {
        Registration addSortListener(SortListener listener);
    }

    class SortEvent extends Component.Event {
        private final Component sortedComponent;
        private final int oldIndex;
        private final int newIndex;

        /**
         * Constructs a new event with the specified source component.
         *
         * @param source          the source component of the event
         * @param sortedComponent
         * @param oldIndex
         * @param newIndex
         */
        public SortEvent(Component source, Component sortedComponent, int oldIndex, int newIndex) {
            super(source);
            this.sortedComponent = sortedComponent;
            this.oldIndex = oldIndex;
            this.newIndex = newIndex;
        }

        public Component getSortedComponent() {
            return sortedComponent;
        }

        public int getOldIndex() {
            return oldIndex;
        }

        public int getNewIndex() {
            return newIndex;
        }
    }
}