package com.evansofts.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(commandDescription = "validate a configuration file")
public class ValidateCommand {
    @Parameter(names = "--config")
    private String fileName;


    public void run() {
        System.out.println("Validation: OK");
    }
}