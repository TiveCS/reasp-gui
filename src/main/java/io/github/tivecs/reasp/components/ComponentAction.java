package io.github.tivecs.reasp.components;

import io.github.tivecs.reasp.events.ItemComponentPreRenderEvent;
import io.github.tivecs.reasp.events.ItemComponentRenderedEvent;
import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ComponentAction<T> {
    default void onComponentInteracted(T componentObject, MenuObject menuObject, InventoryClickEvent event) {}

    default void onComponentPreRender(ItemComponentPreRenderEvent event) {}

    default void onComponentRendered(ItemComponentRenderedEvent event) {}
}
