package io.github.tivecs.reasp.components.object;

import io.github.tivecs.reasp.components.ItemComponent;
import io.github.tivecs.reasp.menu.Menu;
import io.github.tivecs.reasp.menu.object.MenuObject;
import io.github.tivecs.reasp.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class ItemComponentObject {

    private final MenuObject menuObject;
    private final ItemComponent component;
    private final HashMap<String, Object> objectStates = new HashMap<>();

    private final ItemStack templateItem;
    private ItemStack item = null;

    public ItemComponentObject(ItemComponent component, MenuObject menuObject) {
        this.component = component;
        this.menuObject = menuObject;
        objectStates.putAll(component.getStates());

        this.templateItem = prepareTemplate();
    }

    private ItemStack prepareTemplate(){
        Material material = component.getMaterial();
        int amount = component.getAmount();
        String displayName = component.getDisplayName();
        List<String> lore = component.getLore();

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            if (displayName != null) meta.setDisplayName(StringUtils.colored(displayName));
            if (lore != null) {
                StringUtils.colored(lore);
                meta.setLore(lore);
            }
        }
        item.setItemMeta(meta);
        return item;
    }

    public void updateItem(){
        // TODO Update item when state is updated
        this.item = templateItem.clone();
    }

    public void updateState(String key, Object value){
        objectStates.put(key, value);
        if (checkStateIsUpdated(key, value)) updateItem();
    }

    private boolean checkStateIsUpdated(String key, Object value){
        return !objectStates.get(key).equals(value);
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemStack getTemplateItem() {
        return templateItem;
    }

    public Object getState(String key){
        return objectStates.get(key);
    }

    public ItemComponent getComponent() {
        return component;
    }

    public MenuObject getMenuObject() {
        return menuObject;
    }
}
