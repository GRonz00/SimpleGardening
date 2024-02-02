package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.List;

public class HomeViewCLI {
    public int getAction() throws IOException {
        return CLIReader.multiChoice(List.of("add plant","send request","add reminder","see weather forecast","send messages","logout"));
    }

    public void init(String name, String size, String type){
        System.out.println(name+" "+size+" "+type);

    }
}
