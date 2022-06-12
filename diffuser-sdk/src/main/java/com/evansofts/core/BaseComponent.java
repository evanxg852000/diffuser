package com.evansofts.core;

import com.electronwill.nightconfig.core.Config;

public abstract class BaseComponent implements IComponent {
    public static class LoopMessage{}

    protected Config config;

    public Config getConfig() {
        return this.config;
    }

    public String instanceId() {
        return this.getConfig().get("id");
    }


}
