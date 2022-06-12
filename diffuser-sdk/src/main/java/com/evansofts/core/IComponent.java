package com.evansofts.core;

import com.electronwill.nightconfig.core.Config;

public interface IComponent {
    // Returns the instance id from the config.
    String instanceId();

    // Returns the config of this IComponent.
    Config getConfig();

    // Validates config and creates an instance of this IComponent.
    IComponent createInstance(Config config);

    void onReceive(Context ctx, Object msg);
}
