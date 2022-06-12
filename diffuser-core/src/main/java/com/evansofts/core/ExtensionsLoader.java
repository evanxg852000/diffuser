package com.evansofts.core;

import com.evansofts.core.Extension;
import com.evansofts.core.ExtensionRegistry;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.io.File;
import java.io.FilenameFilter;

// Extension naming convention: org.diffuser.FooExtension.jar

public class ExtensionsLoader {
    private File directory;
    private JarClassLoader classLoader;
    private File[] jarFiles;

    public ExtensionsLoader(String path) {
        this.directory = new File(path);
        this.classLoader = new JarClassLoader();
        this.jarFiles = this.directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        });
        for(File jarFile: this.jarFiles) {
            this.classLoader.add(jarFile.getAbsolutePath());
        }
    }

    public void loadExtensions(ExtensionRegistry registry) {
        JclObjectFactory objectFactory = JclObjectFactory.getInstance();
        for(File jarFile: this.jarFiles) {
            String className = jarFile.getName().replace(".jar", "");
            //TODO: check loading error & log
            Extension extension = (Extension) objectFactory.create(this.classLoader, className);
            //TODO: check init error & log
            extension.initialize(registry);
        }
    }

}

