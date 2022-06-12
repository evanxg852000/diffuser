package com.evansofts.core;

import com.electronwill.nightconfig.core.Config;
import com.evansofts.actors.Universe;
import com.evansofts.natives.sources.DummySource;

import java.util.HashMap;

public class Engine implements ExtensionRegistry {
    public static final  String DEFAULT_EXTENSIONS_DIR = "./extensions";

    private HashMap<String, ExtensionItem> items;
    private Universe universe;
    private ExtensionsLoader extensionLoader;

    public Engine(String extensionDir) {
        this.items = new HashMap<>();
        this.universe = new Universe();
        this.extensionLoader = new ExtensionsLoader(extensionDir);
        this.extensionLoader.loadExtensions(this);
    }

    public Universe getUniverse() {
        return this.universe;
    }

    // Resolves dependency tree and creates all actors within the universe.
    public void initUniverse(Config config) {
        //TODO:
        config = Config.inMemory();
        config.set("interval", 2000);
        this.universe.spawnActor(new DummySource(config));
    }

    @Override
    public void registerItem(String id, ExtensionItem item) {
        this.items.put(id, item);
    }

    // Validates the config checking the actor's config and their dependency for cycle
    public void validateConfig(Config config) {
        //TODO:
    }

    // Lists the available exentension and description
    public void listExtensions() {
        //TODO:
    }
}