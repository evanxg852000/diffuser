package com.evansofts;

import com.evansofts.cli.DiffuserCli;


import java.util.logging.Logger;

public class Program {
    static Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        DiffuserCli diffuserCli =  new DiffuserCli();
        diffuserCli.run(args);
    }
}