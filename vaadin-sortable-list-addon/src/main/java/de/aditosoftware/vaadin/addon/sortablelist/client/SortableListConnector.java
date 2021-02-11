package de.aditosoftware.vaadin.addon.sortablelist.client;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.Profiler;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractLayoutConnector;
import com.vaadin.client.ui.VCssLayout;
import com.vaadin.shared.ui.Connect;
import de.aditosoftware.vaadin.addon.sortablelist.SortableList;
import de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableBindings;
import de.aditosoftware.vaadin.addon.sortablelist.client.bindings.SortableInstance;
import de.aditosoftware.vaadin.addon.sortablelist.client.resources.SortableResourceLoader;
import de.aditosoftware.vaadin.addon.sortablelist.client.rpc.SortableListServerRpc;

@Connect(SortableList.class)
public class SortableListConnector extends AbstractLayoutConnector {
    private SortableInstance currentSortableInstance;

    @Override
    protected void init() {
        super.init();

        // Make sure Sortable is loaded and available.
        SortableResourceLoader.ensureInitialized();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (currentSortableInstance != null) {
            // If there is currently a Sortable instance, we just destroy it entirely.
            SortableBindings.destroySortable(currentSortableInstance);
        }

        // Create a new Sortable instance for the element of this connector.
        SortableInstance sortable = SortableBindings.createSortable(getWidget().getElement(), getState());

        // Create a listener for when the user stop sorting.
        SortableBindings.addSortableStopListener(sortable, pO -> {
            // Send the new index of the element to the server to persist the location.
            getRpcProxy(SortableListServerRpc.class).sorted(pO.oldIndex, pO.newIndex);
        });
    }

    @Override
    public SortableListState getState() {
        return (SortableListState) super.getState();
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event) {
        Profiler.enter("CssLayoutConnector.onConnectorHierarchyChange");

        // Detach old child widgets and possibly their caption
        Profiler.enter(
                "CssLayoutConnector.onConnectorHierarchyChange remove old children");
        for (ComponentConnector child : event.getOldChildren()) {
            if (child.getParent() == this) {
                // Skip current children
                continue;
            }
            getWidget().remove(child.getWidget());
        }
        Profiler.leave(
                "CssLayoutConnector.onConnectorHierarchyChange remove old children");

        Profiler.enter(
                "CssLayoutConnector.onConnectorHierarchyChange add children");
        int index = 0;
        for (ComponentConnector child : getChildComponents()) {
            getWidget().addOrMove(child.getWidget(), index++);
        }
        Profiler.leave(
                "CssLayoutConnector.onConnectorHierarchyChange add children");

        Profiler.leave("CssLayoutConnector.onConnectorHierarchyChange");
    }

    @Override
    public VCssLayout getWidget() {
        return (VCssLayout) super.getWidget();
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        // Not implemented.
    }
}
