package de.aditosoftware.vaadin.addon.client;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.*;
import com.vaadin.client.ui.*;
import com.vaadin.shared.ui.Connect;
import de.aditosoftware.vaadin.addon.SortableList;
import de.aditosoftware.vaadin.addon.client.adapter.SortableAdapter;
import de.aditosoftware.vaadin.addon.client.resources.SortableResourceLoader;

@Connect(SortableList.class)
public class SortableListConnector extends AbstractLayoutConnector
{
  private final FastStringMap<VCaption> childIdToCaption = FastStringMap
      .create();

  @Override
  protected void init()
  {
    super.init();
    // Make sure Sortable is loaded and available.
    SortableResourceLoader.ensureInitialized();

    // Configure the element of the container with Sortable.
    SortableAdapter.configureSortable(getWidget().getElement());
  }

  @Override
  public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event)
  {
    Profiler.enter("CssLayoutConnector.onConnectorHierarchyChange");

    // Detach old child widgets and possibly their caption
    Profiler.enter(
        "CssLayoutConnector.onConnectorHierarchyChange remove old children");
    for (ComponentConnector child : event.getOldChildren())
    {
      if (child.getParent() == this)
      {
        // Skip current children
        continue;
      }
      getWidget().remove(child.getWidget());
      VCaption vCaption = childIdToCaption.get(child.getConnectorId());
      if (vCaption != null)
      {
        childIdToCaption.remove(child.getConnectorId());
        getWidget().remove(vCaption);
      }
    }
    Profiler.leave(
        "CssLayoutConnector.onConnectorHierarchyChange remove old children");

    Profiler.enter(
        "CssLayoutConnector.onConnectorHierarchyChange add children");
    int index = 0;
    for (ComponentConnector child : getChildComponents())
    {
      VCaption childCaption = childIdToCaption
          .get(child.getConnectorId());
      if (childCaption != null)
      {
        getWidget().addOrMove(childCaption, index++);
      }
      getWidget().addOrMove(child.getWidget(), index++);
    }
    Profiler.leave(
        "CssLayoutConnector.onConnectorHierarchyChange add children");

    Profiler.leave("CssLayoutConnector.onConnectorHierarchyChange");
  }

  @Override
  public void updateCaption(ComponentConnector child)
  {
    Widget childWidget = child.getWidget();
    int widgetPosition = getWidget().getWidgetIndex(childWidget);

    String childId = child.getConnectorId();
    VCaption caption = childIdToCaption.get(childId);
    if (VCaption.isNeeded(child))
    {
      if (caption == null)
      {
        caption = new VCaption(child, getConnection());
        childIdToCaption.put(childId, caption);
      }
      if (!caption.isAttached())
      {
        // Insert caption at widget index == before widget
        getWidget().insert(caption, widgetPosition);
      }
      caption.updateCaption();
    }
    else if (caption != null)
    {
      childIdToCaption.remove(childId);
      getWidget().remove(caption);
    }
  }

  @Override
  public VCssLayout getWidget()
  {
    return (VCssLayout) super.getWidget();
  }
}
