package io.github.tivecs.reasp.menu;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface MenuAction {
    MenuActionResult onClick(Menu menu, InventoryClickEvent event);
}
