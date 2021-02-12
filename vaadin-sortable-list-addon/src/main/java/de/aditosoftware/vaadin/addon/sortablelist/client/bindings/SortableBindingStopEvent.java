package de.aditosoftware.vaadin.addon.sortablelist.client.bindings;

public class SortableBindingStopEvent {
  public int oldIndex;
  public int newIndex;

  public SortableBindingStopEvent(int oldIndex, int newIndex) {
    this.oldIndex = oldIndex;
    this.newIndex = newIndex;
  }
}
