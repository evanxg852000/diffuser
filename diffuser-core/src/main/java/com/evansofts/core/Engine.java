package com.evansofts.core;

import java.util.HashMap;

public class Engine implements ExtensionRegistry {
    private HashMap<String, ExtensionItem> items;

    public Engine() {
        this.items = new HashMap<>();
    }

    public boolean validateConfig() {
        return false;
    }

    @Override
    public void registerItem(String id, ExtensionItem item) {
        this.items.put(id, item);
    }
}