package com.evansofts;

import com.evansofts.cli.DiffuserCli;

public class Program {
    public static void main(String[] args) {
        DiffuserCli diffuserCli =  new DiffuserCli();
        diffuserCli.run(args);
    }
}