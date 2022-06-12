package com.evansofts.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

public class DiffuserCli {
    @Parameters(commandDescription = "A data pipeline engine.")
    private static class Diffuser {

    }
    private final JCommander rootCommand;

    public DiffuserCli() {
        this.rootCommand = new JCommander(new Diffuser());
        this.rootCommand.setProgramName("diffuser");
        this.rootCommand.addCommand("serve", new ServeCommand());
        this.rootCommand.addCommand("validate", new ValidateCommand());
        this.rootCommand.addCommand("graph", new GraphCommand());
        this.rootCommand.addCommand("version", new VersionCommand());
    }

    public void run(String[] args) {
        try {
            this.rootCommand.parse(args);
            String parsedCmdName = this.rootCommand.getParsedCommand();
            JCommander jCommander = this.rootCommand.getCommands().get(parsedCmdName);
            if (jCommander == null) {
                this.rootCommand.usage();
                return;
            }

            Object command  = jCommander.getObjects().get(0);
            RunnableCommand runnableCommand = (RunnableCommand) command;
            runnableCommand.run();
//            switch (parsedCmdName) {
//                case "serve":
//                    ((ServeCommand) command).run();
//                    break;
//                case "validate":
//                    ((ValidateCommand) command).run();
//                    break;
//                case "graph":
//                    ((GraphCommand) command).run();
//                    break;
//                case "version":
//                    ((VersionCommand) command).run();
//                    break;
//                default:
//                    this.rootCommand.usage();
//            }

        } catch (ParameterException e) {
            System.err.println(e.getLocalizedMessage());
            this.rootCommand.usage();
        }
    }

}



