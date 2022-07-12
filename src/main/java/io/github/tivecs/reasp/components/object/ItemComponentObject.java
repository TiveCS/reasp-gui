package io.github.tivecs.reasp.components.object;

import io.github.tivecs.reasp.components.ItemComponent;
import io.github.tivecs.reasp.menu.object.MenuObject;
import io.github.tivecs.reasp.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemComponentObject {

    private final MenuObject menuObject;
    private final ItemComponent component;
    private final HashMap<String, Object> objectStates = new HashMap<>();

    private final ItemStack templateItem;
    private ItemStack item = null;

    private boolean isVisible = true;

    private final int slot;

    public ItemComponentObject(ItemComponent component, MenuObject menuObject, int slot) {
        this.component = component;
        this.menuObject = menuObject;
        this.slot = slot;
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
        this.item = templateItem.clone();
        if (isVisible) {
            menuObject.getInventory().setItem(slot, this.item);
        }
    }

    public void updateState(String key, Object value){
        boolean isUpdated = checkStateIsUpdated(key, value);
        objectStates.put(key, value);
        if (isUpdated) updateItem();
    }

    private boolean checkStateIsUpdated(String key, Object value){
        Object found = objectStates.get(key);
        return !Objects.equals(found, value);
    }

    public void setVisible(boolean visible) {
        if (visible){
            menuObject.getInventory().setItem(slot, item);
        }else{
            menuObject.getInventory().clear(slot);
        }
        this.isVisible = visible;
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

    public int getSlot() {
        return slot;
    }

    public Object getStateOrDefault(String key, Object defaultValue){
        return objectStates.getOrDefault(key, defaultValue);
    }

    public ItemComponent getComponent() {
        return component;
    }

    public MenuObject getMenuObject() {
        return menuObject;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
