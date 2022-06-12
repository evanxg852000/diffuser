package com.evansofts.core;

import com.electronwill.nightconfig.core.Config;

public interface IComponent {
    // Returns the instance id from the config.
    String instanceId();

    // Returns the config of this IComponent.
    Config getConfig();

    void onReceive(Context ctx, Object msg);

     // Denotes if this component should send bootstrap message to himself.
     boolean isSelfReferenced();
}
