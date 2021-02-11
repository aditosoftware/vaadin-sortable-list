package de.aditosoftware.vaadin.addon.sortablelist.client.resources;

import com.google.gwt.core.client.GWT;

public class SortableResourceLoader
{
  public static final SortableResourceLoader INSTANCE = GWT.create(SortableResourceLoader.class);

  private static boolean initialized = false;

  public static void ensureInitialized() {
    if (!initialized) {
      INSTANCE.initializeResources();
      initialized = true;
    }
  }

  private void initializeResources () {
    inject(SortableResources.INSTANCE.sortableCore().getText());
  }

  protected static native void inject(String script) /*-{
      $wnd.eval(script);
  }-*/;
}
