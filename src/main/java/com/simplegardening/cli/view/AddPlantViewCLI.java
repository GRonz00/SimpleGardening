package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPlantViewCLI {
    public List<String> getPlant() throws IOException {
        List<String> plant=new ArrayList<>();
        System.out.println("Add plant");
        System.out.print("Insert name: ");
        plant.add(CLIReader.readline());
        System.out.println("Insert type: ");
        plant.add(CLIReader.multiChoiceString(List.of("indoor","outdoor")));
        System.out.print("Insert size: ");
        plant.add(CLIReader.multiChoiceString(List.of("small","medium","large")));
        return plant;
    }
}
