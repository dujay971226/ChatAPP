package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// To be merged.
public class ViewManager implements PropertyChangeListener{
    private final CardLayout cardLayout;
    private final JPanel views;
    private ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            if (evt.getNewValue() instanceof RoomView) {
                if (ContainsRoomView(views)) {
                    RemoveRoomView(views);
                }
                AddRoomView((RoomView) evt.getNewValue());

            }
            else {
                String viewModelName = (String) evt.getNewValue();
                cardLayout.show(views, viewModelName);
            }
        }
    }

    private boolean ContainsRoomView (JPanel views) {
        Component[] components = views.getComponents();
        for (Component component : components) {
            if (component instanceof RoomView) {
                return true;
            }
        }
        return false;
    }

    private void RemoveRoomView(JPanel views) {
        Component[] components = views.getComponents();
        for (Component component : components) {
            if (component instanceof RoomView) {
                views.remove(component);
            }
        }
    }

    public void AddRoomView (RoomView roomView) {
        this.views.add(roomView, roomView.viewName);
    }
}
