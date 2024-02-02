package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestClientViewCLI {
    public int getChoice() throws IOException {
        return CLIReader.multiChoice(List.of("find request","send request"));
    }
    public List<String> request() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.print("Plant name: ");
        s.add(CLIReader.readline());
        System.out.print("Start year-month-day: ");
        s.add(CLIReader.readline());
        System.out.print("End year-month-day: ");
        s.add(CLIReader.readline());
        System.out.println("Pickup: ");
        s.add(CLIReader.multiChoiceString(List.of("true","false")));
        System.out.print("Max km: ");
        s.add(CLIReader.readline());
        return s;
    }

    public String getIdRequestForm() throws IOException {
        System.out.print("Id request form: ");
        return CLIReader.readline();
    }

    public void printPossibleRequest(String pro, String address, String price, Integer idRequestForm) {
        System.out.println(pro+" "+address+" price: "+price+" idRequestForm :"+idRequestForm);
    }
}
