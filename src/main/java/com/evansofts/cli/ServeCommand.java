package com.evansofts.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Start the data pipeline server")
public class ServeCommand {
    @Parameter(names = "--config")
    private String configFileName;


    public void run() {
        System.out.println("Serving: OK");
    }
}