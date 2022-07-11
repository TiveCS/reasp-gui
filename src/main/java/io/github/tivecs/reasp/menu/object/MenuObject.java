package io.github.tivecs.reasp.menu.object;

import io.github.tivecs.reasp.components.ItemComponent;
import io.github.tivecs.reasp.components.MenuComponent;
import io.github.tivecs.reasp.components.PageComponent;
import io.github.tivecs.reasp.components.object.ItemComponentObject;
import io.github.tivecs.reasp.menu.Menu;
import io.github.tivecs.reasp.utils.Validator;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuObject {

    private final Menu menu;

    private int currentPage = 1;

    private Inventory inventory = null;

    private final HashMap<Integer, ItemComponentObject> objectMap = new HashMap<>();

    public MenuObject(Menu menu) {
        this.menu = menu;
    }

    public void renderPage(int page){
        PageComponent pc = getPageComponent(page);
        if (pc == null) throw new NullPointerException("PageComponent on page " + page + " is not found!");

        objectMap.clear();
        renderPageComponents(pc);
    }

    public void renderPageComponents(PageComponent pageComponent){
        List<String> mapping = pageComponent.getMapping();
        int rows = mapping.size();
        this.inventory = Bukkit.createInventory(null, rows*9);

        for (int row = 0; row < mapping.size(); row++) {
            String mapString = mapping.get(row);
            char[] maps = mapString.toCharArray();

            for (int i = 0; i < maps.length; i++) {
                char address = maps[i];
                ItemComponent itemComponent = pageComponent.getComponentByAddress(address);

                if (itemComponent == null) continue;

                int slot = row*9 + i;
                ItemComponentObject itemObject = new ItemComponentObject(itemComponent, this);
                itemObject.updateItem();

                objectMap.put(slot, itemObject);
                this.inventory.setItem(slot, itemObject.getItem());
            }
        }
    }

    public void movePage(int newPage){
        this.currentPage = newPage;
        renderPage(newPage);
    }

    public PageComponent getPageComponent(int page){
        MenuComponent mc = menu.getMenuComponent();
        Optional<PageComponent> optional = mc.getPageComponents().stream()
                .filter(pc -> Validator.numIsIntercept(pc.getMinPage(), pc.getMaxPage(), page))
                .findFirst();

        return optional.orElse(null);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Menu getMenu() {
        return menu;
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
