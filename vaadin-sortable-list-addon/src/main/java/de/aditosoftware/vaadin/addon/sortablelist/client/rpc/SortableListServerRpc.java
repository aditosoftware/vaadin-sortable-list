package de.aditosoftware.vaadin.addon.sortablelist.client.rpc;

import com.vaadin.shared.communication.ServerRpc;

public interface SortableListServerRpc extends ServerRpc {
    /**
     * Updates the order of the elements.
     *
     * @param oldIndex The old index of the element.
     * @param newIndex The new index of the element.
     */
    public void sorted(int oldIndex, int newIndex);
}
