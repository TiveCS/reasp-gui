package io.github.tivecs.reasp;

import io.github.tivecs.reasp.menu.Menu;
import io.github.tivecs.reasp.menu.object.MenuObject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ReaspManager {

    private final JavaPlugin plugin;

    private final HashMap<String, Menu> menus = new HashMap<>();

    public ReaspManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addMenu(Menu... menus){
        for (Menu menu : menus) {
            this.menus.put(menu.getId(), menu);
        }
    }

    public MenuObject open(Player player, String menuId, int page){
        Menu menu = getMenus().get(menuId);
        MenuObject mo = new MenuObject(menu);

        mo.renderPage(page);
        player.openInventory(mo.getInventory());

        return mo;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public HashMap<String, Menu> getMenus() {
        return menus;
    }
}
