package io.github.tivecs.reasp.menu.object;

import io.github.tivecs.reasp.ReaspManager;
import io.github.tivecs.reasp.components.ItemComponent;
import io.github.tivecs.reasp.components.MenuComponent;
import io.github.tivecs.reasp.components.PageComponent;
import io.github.tivecs.reasp.components.object.ItemComponentObject;
import io.github.tivecs.reasp.events.ItemComponentPreRenderEvent;
import io.github.tivecs.reasp.events.ItemComponentRenderedEvent;
import io.github.tivecs.reasp.menu.Menu;
import io.github.tivecs.reasp.utils.StringUtils;
import io.github.tivecs.reasp.utils.Validator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MenuObject {

    private final ReaspManager manager;
    private final Menu menu;

    private int currentPage = 1;

    private PageComponent currentPageComponent = null;

    private Inventory inventory = null;

    private final HashMap<Integer, ItemComponentObject> objectMap = new HashMap<>();

    public MenuObject(ReaspManager manager, Menu menu) {
        this.manager = manager;
        this.menu = menu;
    }

    public void renderPage(int page){
        PageComponent pc = getPageComponent(page);
        if (pc == null) throw new NullPointerException("PageComponent on page " + page + " is not found!");

        this.currentPageComponent = pc;
        renderPageComponents(pc);
    }

    public Inventory prepareInventory(int rows, String title){
        if (title == null){
            return Bukkit.createInventory(null, rows*9);
        }else{
            title = StringUtils.colored(title);
            title = title.replace("%menu_page%", currentPage + "");

            return Bukkit.createInventory(null, rows*9, title);
        }
    }

    public void renderPageComponents(PageComponent pageComponent){
        List<char[]> mapping = pageComponent.getMapping();
        int rows = mapping.size();
        String title = menu.getTitle();
        this.inventory = prepareInventory(rows, title);

        for (int row = 0; row < mapping.size(); row++) {
            char[] maps = mapping.get(row);

            for (int i = 0; i < maps.length; i++) {
                char address = maps[i];
                ItemComponent itemComponent = pageComponent.getComponentByAddress(address);

                if (itemComponent == null) continue;

                int slot = row*9 + i;
                ItemComponentObject itemObject = new ItemComponentObject(itemComponent, this, slot);

                ItemComponentPreRenderEvent preRenderEvent = new ItemComponentPreRenderEvent(itemObject, this);
                Bukkit.getPluginManager().callEvent(preRenderEvent);
                if (preRenderEvent.isCancelled()) continue;

                objectMap.put(slot, itemObject);
                itemObject.updateItem();

                ItemComponentRenderedEvent renderedEvent = new ItemComponentRenderedEvent(itemObject, this);
                Bukkit.getPluginManager().callEvent(renderedEvent);
            }
        }
    }

    public void movePage(int newPage){
        this.currentPage = newPage;
        renderPage(newPage);
    }

    public void open(Player player, int page){
        player.closeInventory();
        manager.getPlayerMenuActivity().put(player.getUniqueId(), this);

        movePage(page);
        player.openInventory(inventory);
    }

    public PageComponent getPageComponent(int page){
        MenuComponent mc = menu.getMenuComponent();
        Optional<PageComponent> optional = mc.getPageComponents().stream()
                .filter(pc -> Validator.numIsIntercept(pc.getMinPage(), pc.getMaxPage(), page))
                .findFirst();

        return optional.orElse(mc.getDefaultPage());
    }

    public ItemComponentObject getItemObjectAtSlot(int slot){
        return objectMap.get(slot);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public PageComponent getCurrentPageComponent() {
        return currentPageComponent;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Menu getMenu() {
        return menu;
    }

    public ReaspManager getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "MenuObject{" +
                "menu=" + menu +
                ", currentPage=" + currentPage +
                ", inventory=" + inventory +
                ", objectMap=" + objectMap +
                '}';
    }
}
