package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReminderViewCLI {
    public List<String> getReminder() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.print("Plant name: ");
        s.add(CLIReader.readline());
        System.out.println("Type: ");
        s.add(CLIReader.multiChoiceString(List.of("WATER", "NEBULIZE", "FERTILIZE")));
        System.out.print("Hour: ");
        s.add(CLIReader.readline());
        System.out.print("Min: ");
        s.add(CLIReader.readline());
        return s;
    }
}
