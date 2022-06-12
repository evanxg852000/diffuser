package com.evansofts.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "export a configuration as graph")
public class GraphCommand implements RunnableCommand {
    @Parameter(names = "--config")
    private String fileName;

    public void run() {
        System.out.println("Graph: OK");
    }
}