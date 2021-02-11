package de.aditosoftware.vaadin.addon.sortablelist;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import de.aditosoftware.vaadin.addon.sortablelist.client.SortableListState;
import de.aditosoftware.vaadin.addon.sortablelist.client.rpc.SortableListServerRpc;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SortableList extends AbstractLayout {
    //Holds all registered components of this layout.
    private final LinkedList<Component> components = new LinkedList<>();

    public SortableList() {
        _registerServerRPC();
    }

    @Override
    public void addComponent(Component c) {
        // Add to components before calling super.addComponent
        // so that it is available to AttachListeners
        components.add(c);
        try {
            super.addComponent(c);
        } catch (IllegalArgumentException e) {
            components.remove(c);
            throw e;
        }
    }

    /**
     * Adds a component into this container. The component is added to the left
     * or on top of the other components.
     *
     * @param c the component to be added.
     */
    public void addComponentAsFirst(Component c) {
        // If c is already in this, we must remove it before proceeding
        // see ticket #7668
        if (equals(c.getParent())) {
            removeComponent(c);
        }
        components.addFirst(c);
        try {
            super.addComponent(c);
        } catch (IllegalArgumentException e) {
            components.remove(c);
            throw e;
        }
    }

    /**
     * Adds a component into indexed position in this container.
     *
     * @param c     the component to be added.
     * @param index the index of the component position. The components currently
     *              in and after the position are shifted forwards.
     */
    public void addComponent(Component c, int index) {
        // If c is already in this, we must remove it before proceeding
        // see ticket #7668
        if (equals(c.getParent())) {
            // When c is removed, all components after it are shifted down
            if (index > getComponentIndex(c)) {
                index--;
            }
            removeComponent(c);
        }
        components.add(index, c);
        try {
            super.addComponent(c);
        } catch (IllegalArgumentException e) {
            components.remove(c);
            throw e;
        }
    }

    /**
     * Removes the component from this container.
     *
     * @param c the component to be removed.
     */
    @Override
    public void removeComponent(Component c) {
        components.remove(c);
        super.removeComponent(c);
    }

    /**
     * Gets the component container iterator for going trough all the components
     * in the container.
     *
     * @return the Iterator of the components inside the container.
     */
    @Override
    public Iterator<Component> iterator() {
        return Collections.unmodifiableCollection(components).iterator();
    }

    /**
     * Gets the number of contained components. Consistent with the iterator
     * returned by {@link #getComponentIterator()}.
     *
     * @return the number of contained components
     */
    @Override
    public int getComponentCount() {
        return components.size();
    }

    /**
     * Returns styles to be applied to given component. Override this method to
     * inject custom style rules to components.
     *
     * <p>
     * Note that styles are injected over previous styles before actual child
     * rendering. Previous styles are not cleared, but overridden.
     *
     * <p>
     * Note that one most often achieves better code style, by separating
     * styling to theme (with custom theme and {@link #addStyleName(String)}.
     * With own custom styles it is also very easy to break browser
     * compatibility.
     *
     * @param c the component
     * @return css rules to be applied to component
     */
    protected String getCss(Component c) {
        return null;
    }

    /* Documented in superclass */
    @Override
    public void replaceComponent(Component oldComponent,
                                 Component newComponent) {

        // Gets the locations
        int oldLocation = -1;
        int newLocation = -1;
        int location = 0;
        for (final Component component : components) {
            if (component == oldComponent) {
                oldLocation = location;
            }
            if (component == newComponent) {
                newLocation = location;
            }

            location++;
        }

        if (oldLocation == -1) {
            addComponent(newComponent);
        } else if (newLocation == -1) {
            removeComponent(oldComponent);
            addComponent(newComponent, oldLocation);
        } else {
            if (oldLocation > newLocation) {
                components.remove(oldComponent);
                components.add(newLocation, oldComponent);
                components.remove(newComponent);
                components.add(oldLocation, newComponent);
            } else {
                components.remove(newComponent);
                components.add(oldLocation, newComponent);
                components.remove(oldComponent);
                components.add(newLocation, oldComponent);
            }

            markAsDirty();
        }
    }

    /**
     * Returns the index of the given component.
     *
     * @param component The component to look up.
     * @return The index of the component or -1 if the component is not a child.
     */
    public int getComponentIndex(Component component) {
        return components.indexOf(component);
    }

    /**
     * Returns the component at the given position.
     *
     * @param index The position of the component.
     * @return The component at the given index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Component getComponent(int index) throws IndexOutOfBoundsException {
        return components.get(index);
    }

    @Override
    protected SortableListState getState() {
        return (SortableListState) super.getState();
    }

    @Override
    protected SortableListState getState(boolean markAsDirty) {
        return (SortableListState) super.getState(markAsDirty);
    }

    public void setDraggable(String pDraggable) {
        getState().draggable = pDraggable;
    }

    public void setHandle(String pHandle) {
        getState().handle = pHandle;
    }

    public void setMirrorXAxis(boolean pValue) {
        getState().mirrorXAxis = pValue;
    }

    public void setMirrorYAxis(boolean pValue) {
        getState().mirrorYAxis = pValue;
    }

    public void setMirrorConstrainDimensions(boolean pValue) {
        getState().mirrorConstrainDimensions = pValue;
    }

    public String getDraggable() {
        return getState(false).draggable;
    }

    public String getHandle() {
        return getState(false).handle;
    }

    public boolean getMirrorXAxis() {
        return getState(false).mirrorXAxis;
    }

    public boolean getMirrorYAxis() {
        return getState(false).mirrorYAxis;
    }

    public boolean getMirrorConstrainDimensions() {
        return getState(false).mirrorConstrainDimensions;
    }

    private void _registerServerRPC() {
        registerRpc(new SortableListServerRpc() {
            @Override
            public void sorted(int oldIndex, int newIndex) {
                Component oldComponent = getComponent(oldIndex);
                removeComponent(oldComponent);
                addComponent(oldComponent, newIndex);

            }
        }, SortableListServerRpc.class);
    }
}
