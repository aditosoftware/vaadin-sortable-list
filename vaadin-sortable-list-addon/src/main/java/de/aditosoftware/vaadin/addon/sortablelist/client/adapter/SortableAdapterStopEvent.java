package de.aditosoftware.vaadin.addon.sortablelist.client.adapter;

public class SortableAdapterStopEvent {
    public int oldIndex;
    public int newIndex;

    public SortableAdapterStopEvent(int oldIndex, int newIndex) {
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }
}
