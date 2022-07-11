package io.github.tivecs.reasp.components;

import io.github.tivecs.reasp.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class MenuComponent extends Component {

    private final List<PageComponent> pageComponents = new ArrayList<>();

    private PageComponent defaultPage = null;

    public void addPages(PageComponent... components){
        for (PageComponent component : components) {
            if (validateNoPageConflict(component)){
                pageComponents.add(component);

                if (defaultPage == null) defaultPage = component;
            }
        }
    }

    public boolean validateNoPageConflict(PageComponent c){
        return pageComponents.stream()
                .noneMatch(pc -> Validator.intervalIsIntercept(pc.getMinPage(), pc.getMaxPage(), c.getMinPage(), c.getMaxPage()));
    }

    public boolean hasDefaultPage(){
        return defaultPage != null;
    }

    public void setDefaultPage(PageComponent defaultPage) {
        this.defaultPage = defaultPage;
    }

    public PageComponent getDefaultPage() {
        return defaultPage;
    }

    public List<PageComponent> getPageComponents() {
        return pageComponents;
    }

    @Override
    public String toString() {
        return "MenuComponent{" +
                "pageComponents=" + pageComponents +
                ", defaultPage=" + defaultPage +
                '}';
    }
}
