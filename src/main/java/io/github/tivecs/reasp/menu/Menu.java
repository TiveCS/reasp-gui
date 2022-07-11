package io.github.tivecs.reasp.menu;

import io.github.tivecs.reasp.components.MenuComponent;

public class Menu {

    private final String id;
    private final MenuComponent menuComponent;

    private MenuAction menuAction = null;

    public Menu(String id) {
        this.id = id;
        this.menuComponent = new MenuComponent();
    }

    public void setMenuAction(MenuAction menuAction) {
        this.menuAction = menuAction;
    }

    public MenuAction getMenuAction() {
        return menuAction;
    }

    public String getId() {
        return id;
    }

    public MenuComponent getMenuComponent() {
        return menuComponent;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", menuComponent=" + menuComponent +
                ", menuAction=" + menuAction +
                '}';
    }
}
