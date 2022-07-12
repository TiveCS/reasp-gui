package io.github.tivecs.reasp.events;

import io.github.tivecs.reasp.components.object.ItemComponentObject;
import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ItemComponentPreRenderEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemComponentObject itemObject;
    private final MenuObject menuObject;

    private boolean isCancelled = false;

    public ItemComponentPreRenderEvent(ItemComponentObject itemObject, MenuObject menuObject) {
        this.itemObject = itemObject;
        this.menuObject = menuObject;
    }

    public ItemComponentObject getItemObject() {
        return itemObject;
    }

    public MenuObject getMenuObject() {
        return menuObject;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
}
