package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.awt.*;
import java.util.ArrayList;

public class Menus extends MenuComponent{
    ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;
    String description;

    public Menus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(DinerMenu menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return (MenuComponent)menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MenuItem[] getItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        for (MenuComponent menuComponent : menuComponents) {
            MenuItem[] childItems = menuComponent.getItems();
            for (MenuItem item : childItems) {
                items.add(item);
            }
        }
        return items.toArray(new MenuItem[0]);
    }
}