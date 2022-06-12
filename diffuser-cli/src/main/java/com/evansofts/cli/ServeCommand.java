package com.evansofts.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.evansofts.actors.Universe;
import com.evansofts.config.Configuration;
import com.evansofts.web.WebServer;


import java.io.IOException;
import java.util.logging.Logger;

@Parameters(commandDescription = "Start the data pipeline server")
public class ServeCommand implements RunnableCommand {
    static Logger logger = Logger.getLogger(ServeCommand.class.getName());

    @Parameter(names = "--config")
    private String configFileName;


    public void run() {
        try {
            Configuration configurator = new Configuration();
            FileConfig config = configurator.load(this.configFileName);
            Universe universe = new Universe();
            logger.info("Start serving...");
            new WebServer(config, universe).start();
            System.out.println("Serving: OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}