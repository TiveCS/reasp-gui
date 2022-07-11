package io.github.tivecs.reasp.components;

import java.util.HashMap;

public class Component {

    private final HashMap<String, Object> states = new HashMap<>();

    public void setState(String key, Object value) {
        states.put(key, value);
    }

    public Object getState(String key) {
        return states.get(key);
    }

    public HashMap<String, Object> getStates() {
        return states;
    }


}
