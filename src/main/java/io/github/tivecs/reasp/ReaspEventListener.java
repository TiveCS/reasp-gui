package io.github.tivecs.reasp;

import io.github.tivecs.reasp.components.ComponentAction;
import io.github.tivecs.reasp.components.object.ItemComponentObject;
import io.github.tivecs.reasp.events.ItemComponentPreRenderEvent;
import io.github.tivecs.reasp.events.ItemComponentRenderedEvent;
import io.github.tivecs.reasp.events.MenuCloseEvent;
import io.github.tivecs.reasp.menu.Menu;
import io.github.tivecs.reasp.menu.MenuAction;
import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class ReaspEventListener implements Listener {

    private final ReaspManager manager;

    public ReaspEventListener(ReaspManager manager) {
        this.manager = manager;
        Bukkit.getPluginManager().registerEvents(this, manager.getPlugin());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        HumanEntity p = event.getWhoClicked();
        UUID uuid = p.getUniqueId();
        int slot = event.getSlot();

        MenuObject mo = manager.getPlayerMenuActivity().get(uuid);

        if (mo != null) {
            Menu m = mo.getMenu();

            MenuAction ma = m.getMenuAction();
            if (ma != null) {
                ma.onClick(mo, event);
            }

            ItemComponentObject ico = mo.getItemObjectAtSlot(slot);
            ComponentAction<ItemComponentObject> itemAction = ico.getComponent().getAction();
            if (itemAction != null){
                itemAction.onComponentInteracted(ico, mo, event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        MenuObject mo = manager.getPlayerMenuActivity().get(uuid);
        if (mo != null){
            MenuCloseEvent closeEvent = new MenuCloseEvent(mo, event);
            Bukkit.getPluginManager().callEvent(closeEvent);
            manager.getPlayerMenuActivity().remove(uuid);
        }
    }

    @EventHandler
    public void onItemComponentPreRender(ItemComponentPreRenderEvent event){
        ComponentAction<ItemComponentObject> action = event.getItemObject().getComponent().getAction();
        if (action != null) action.onComponentPreRender(event);
    }

    @EventHandler
    public void onItemComponentRender(ItemComponentRenderedEvent event){
        ComponentAction<ItemComponentObject> action = event.getItemObject().getComponent().getAction();
        if (action != null) action.onComponentRendered(event);
    }

}
