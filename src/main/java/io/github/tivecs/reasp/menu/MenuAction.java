package io.github.tivecs.reasp.menu;

import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface MenuAction {
    MenuActionResult onClick(MenuObject menuObject, InventoryClickEvent event);
}
