package de.aditosoftware.vaadin.addon.sortablelist.demo;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import de.aditosoftware.vaadin.addon.sortablelist.SortableList;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@PreserveOnRefresh
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // Initialize our new UI component
        final SortableList list = new SortableList();
        list.addStyleName("sortable-list");
        list.setDraggable(".list-bar");
        list.setMirrorYAxis(true);
        list.addSortListener(sortEvent -> {
            System.out.println("Sorted");
        });

        list.addComponent(getBar(1));
        list.addComponent(getBar(2));
        list.addComponent(getBar(3));
        list.addComponent(getBar(4));


        setContent(list);
    }

    private Component getBar(int number) {
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
