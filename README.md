# Sortable List addon for Vaadin 8
Provides a component which lets the user sort its children components. This is basically just a wrapper for Sortable of [Shopify/draggable](https://github.com/Shopify/draggable).

## Download release
Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to https://vaadin.com/addon/vaadin-sortable-list

## Getting started
Here is a simple example on how to try out the add-on.
```java
// Create the component.
SortableList list = new SortableList();

// Set the CSS selector for the draggable elements.
list.setDraggable(".list-bar");

// Add a listener which will be called when the user sorts the list.
list.addSortListener(sortEvent -> {
    // ...
});

// Add any components you'd like.
list.addComponent(new Label("1"));
list.addComponent(new Label("2"));
list.addComponent(new Label("3"));
list.addComponent(new Label("4"));
```
