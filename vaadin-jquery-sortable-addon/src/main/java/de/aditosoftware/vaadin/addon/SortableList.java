package de.aditosoftware.vaadin.addon;

import com.vaadin.ui.*;
import de.aditosoftware.vaadin.addon.client.shared.*;

import java.util.*;

public class SortableList extends AbstractLayout
{
  public SortableList(SortableOptions pOptions)
  {
    getState().options = pOptions;
  }

  /**
   * Holds all registered components of this layout.
   */
  private final List<Component> components = new LinkedList<>();

  @Override
  public void replaceComponent(Component oldComponent, Component newComponent)
  {
    // Gets the locations
    int oldLocation = -1;
    int newLocation = -1;
    int location = 0;
    for (final Component component : components)
    {
      if (component == oldComponent)
        oldLocation = location;
      if (component == newComponent)
        newLocation = location;

      location++;
    }

    if (oldLocation == -1)
    {
      addComponent(newComponent);
    }
    else if (newLocation == -1)
    {

      removeComponent(oldComponent);
      addComponent(newComponent, oldLocation);
    }
    else
    {
      // Both old and new are in the layout
      if (oldLocation > newLocation)
      {
        components.remove(oldComponent);
        components.add(newLocation, oldComponent);
        components.remove(newComponent);
        components.add(oldLocation, newComponent);
      }
      else
      {
        components.remove(newComponent);
        components.add(oldLocation, newComponent);
        components.remove(oldComponent);
        components.add(newLocation, oldComponent);
      }

      markAsDirty();
    }
  }

  @Override
  public int getComponentCount()
  {
    return components.size();
  }

  @Override
  public Iterator<Component> iterator()
  {
    return Collections.unmodifiableList(components).iterator();
  }

  @Override
  public void addComponent(Component c)
  {
    // Add to components before calling super.addComponent
    // so that it is available to AttachListeners
    components.add(c);
    try
    {
      super.addComponent(c);
    }
    catch (IllegalArgumentException e)
    {
      components.remove(c);
      throw e;
    }
  }

  public void addComponent(Component c, int index)
  {
    // If c is already in this, we must remove it before proceeding
    // see ticket #7668
    if (equals(c.getParent()))
    {
      // When c is removed, all components after it are shifted down
      if (index > getComponentIndex(c))
      {
        index--;
      }
      removeComponent(c);
    }
    components.add(index, c);
    try
    {
      super.addComponent(c);
    }
    catch (IllegalArgumentException e)
    {
      components.remove(c);
      throw e;
    }
  }

  public void addComponentAsFirst(Component c)
  {
    // If c is already in this, we must remove it before proceeding
    // see ticket #7668
    if (equals(c.getParent()))
    {
      removeComponent(c);
    }
    components.add(0, c);
    try
    {
      super.addComponent(c);
    }
    catch (IllegalArgumentException e)
    {
      components.remove(c);
      throw e;
    }
  }

  public int getComponentIndex(Component component)
  {
    return components.indexOf(component);
  }

  @Override
  protected SortableListState getState()
  {
    return (SortableListState) super.getState();
  }
}
