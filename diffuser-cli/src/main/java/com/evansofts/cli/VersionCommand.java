package com.evansofts.cli;

import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "print product version and exit.")
public class VersionCommand implements RunnableCommand {
    public void run() {
        System.out.println("0.0.1");
    }
}