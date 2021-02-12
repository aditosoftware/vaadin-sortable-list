package de.aditosoftware.vaadin.addon.sortablelist.client.bindings;

import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;

/** Holds a Sortable instance. This is basically just described by a {@link JavaScriptObject}. */
public class SortableInstance implements Serializable {
  final JavaScriptObject instance;

  public SortableInstance() {
    instance = null;
  }

  public SortableInstance(JavaScriptObject instance) {
    this.instance = instance;
  }
}
