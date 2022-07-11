package io.github.tivecs.reasp.components;

import org.bukkit.Material;

import java.util.List;

public class ItemComponent extends Component {

    private final String id;
    private String displayName = null;
    private int amount = 1;
    private final Material material;

    private List<String> lore = null;

    public ItemComponent(String id, String displayName, int amount, Material material, List<String> lore) {
        this.id = id;
        this.displayName = displayName;
        this.amount = amount;
        this.material = material;
        this.lore = lore;
    }

    public ItemComponent(String id, String displayName, Material material, List<String> lore) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
        this.lore = lore;
    }

    public ItemComponent(String id, String displayName, Material material) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
    }

    public ItemComponent(String id, Material material, List<String> lore) {
        this.id = id;
        this.material = material;
        this.lore = lore;
    }

    public ItemComponent(String id, Material material) {
        this.id = id;
        this.material = material;
    }

    public String getId() {
        return id;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "ItemComponent{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", amount=" + amount +
                ", material=" + material +
                ", lore=" + lore +
                '}';
    }
}
