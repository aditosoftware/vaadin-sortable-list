package de.aditosoftware.vaadin.addon.sortablelist.demo;

import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import de.aditosoftware.vaadin.addon.sortablelist.SortableList;
import de.aditosoftware.vaadin.addon.sortablelist.client.shared.SortableOptions;
import de.aditosoftware.vaadin.addon.sortablelist.configuration.SortableOptionsBuilder;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
  public static class Servlet extends VaadinServlet
  {
  }

  @Override
  protected void init(VaadinRequest request)
  {
    final SortableOptions options = new SortableOptionsBuilder()
        .setDraggable(".list-bar")
        //.setHandle(".bar-handle")
        .setMirrorYAxis(true)
        .build();

    // Initialize our new UI component
    final SortableList list = new SortableList(options);
    list.addStyleName("sortable-list");

    list.addComponent(getBar(1));
    list.addComponent(getBar(2));
    list.addComponent(getBar(3));
    list.addComponent(getBar(4));


    setContent(list);
  }

  private Component getBar (int number) {
    CssLayout layout = new CssLayout();
    layout.addStyleName("list-bar");

    Label label = new Label();
    label.setIcon(VaadinIcons.CIRCLE);
    label.addStyleName("bar-handle");
    layout.addComponent(label);
    layout.addComponent(new Label("Container Number " + number));


    return layout;
  }
}
