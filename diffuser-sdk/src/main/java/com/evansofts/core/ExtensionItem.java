package com.evansofts.core;

import com.electronwill.nightconfig.core.Config;

public interface ExtensionItem {
    ExtensionIdentity describe();
    IComponent createInstance(Config config);
}
