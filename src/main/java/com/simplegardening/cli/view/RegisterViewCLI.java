package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterViewCLI {
    public List<String> getAddress() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.println("Register");
        System.out.print("Insert username: ");
        s.add(CLIReader.readline());
        System.out.print("Insert password: ");
        s.add(CLIReader.readline());
        System.out.print("Type of user: ");
        s.add(CLIReader.multiChoiceString(List.of("Client","Pro")));
        System.out.print("Insert nation: ");
        s.add(CLIReader.readline());
        System.out.print("Insert city: ");
        s.add(CLIReader.readline());
        System.out.print("Insert postal code: ");
        s.add(CLIReader.readline());
        System.out.print("Insert street: ");
        s.add(CLIReader.readline());
        System.out.print("Insert num: ");
        s.add(CLIReader.readline());
        return s;

    }
}
