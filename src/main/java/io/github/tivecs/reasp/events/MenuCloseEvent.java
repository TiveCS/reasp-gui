package io.github.tivecs.reasp.events;

import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MenuCloseEvent extends Event {

    private static HandlerList HANDLER_LIST = new HandlerList();

    private MenuObject menuObject;
    private InventoryCloseEvent parentEvent;

    public MenuCloseEvent(MenuObject menuObject, InventoryCloseEvent event) {
        this.menuObject = menuObject;
        this.parentEvent = event;
    }

    public MenuObject getMenuObject() {
        return menuObject;
    }

    public InventoryCloseEvent getParentEvent() {
        return parentEvent;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
