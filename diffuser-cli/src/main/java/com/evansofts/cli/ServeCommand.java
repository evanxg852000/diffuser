package com.evansofts.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.evansofts.config.Configuration;
import com.evansofts.core.Engine;
import com.evansofts.web.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

@Parameters(commandDescription = "Start the data pipeline server")
public class ServeCommand implements RunnableCommand {
    static final Logger logger = LoggerFactory.getLogger(ServeCommand.class);

    @Parameter(names = "--config")
    private String configFileName;


    public void run() {
        try {
            Configuration configurator = new Configuration();
            FileConfig config = configurator.load(this.configFileName);
            Engine engine = new Engine(Engine.DEFAULT_EXTENSIONS_DIR);
            engine.initUniverse(config);

            new WebServer(config, engine.getUniverse()).start();
            System.out.println("Serving: OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}