package io.github.tivecs.reasp.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageComponent extends Component {

    private final int minPage;
    private final int maxPage;

    private final HashMap<String, ItemComponent> itemComponents = new HashMap<>();
    private final HashMap<Character, String> componentMap = new HashMap<>();

    private final List<char[]> mapping;
    
    public PageComponent(int minPage, int maxPage) {
        this.minPage = minPage;
        this.maxPage = maxPage;
        this.mapping = new ArrayList<>();
    }

    public PageComponent(int minPage, int maxPage, List<char[]> mapping) {
        this.minPage = minPage;
        this.maxPage = maxPage;
        this.mapping = mapping;
    }

    public PageComponent addMapping(String map){
        mapping.add(map.toCharArray());
        return this;
    }

    public PageComponent addMapping(char[] map){
        mapping.add(map);
        return this;
    }

    public PageComponent addItemComponent(char mapAddress, ItemComponent component){
        itemComponents.put(component.getId(), component);
        componentMap.put(mapAddress, component.getId());
        return this;
    }

    public ItemComponent getComponent(String id){
        return itemComponents.get(id);
    }

    public String getComponentId(char mapAddress){
        return componentMap.get(mapAddress);
    }

    public ItemComponent getComponentByAddress(char mapAddress){
        return itemComponents.get(getComponentId(mapAddress));
    }

    public List<char[]> getMapping() {
        return mapping;
    }

    public int getMinPage() {
        return minPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    @Override
    public String toString() {
        return "PageComponent{" +
                "minPage=" + minPage +
                ", maxPage=" + maxPage +
                ", itemComponents=" + itemComponents +
                ", componentMap=" + componentMap +
                ", mapping=" + mapping +
                '}';
    }
}
