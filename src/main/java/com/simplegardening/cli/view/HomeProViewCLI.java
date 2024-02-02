package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeProViewCLI {
    public void init(){
        System.out.println("request");
    }
    public int getAction() throws IOException {
        return CLIReader.multiChoice(List.of("add request form","accept request","refuse request","send message","logout"));
    }

    public void viewRequest(String client, String plant, String size, String type, String price, String start, String end, String state, String idRequestForm){
            System.out.println(client+" plant "+plant+" "+size+" "+type+" price: "+price+" start: "+start+" end: "+end+" state: "+state+" id request form: "+ idRequestForm);
    }
    public List<String> addRequestForm() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.print("Start year-month-day: ");
        s.add(CLIReader.readline());
        System.out.print("End year-month-day: ");
        s.add(CLIReader.readline());
        System.out.println("Price base: ");
        s.add(CLIReader.readline());
        System.out.println("Pickup: ");
        s.add(CLIReader.multiChoiceString(List.of("true","false")));
        System.out.print("Max km: ");
        s.add(CLIReader.readline());
        System.out.println("Price base pickup: ");
        s.add(CLIReader.readline());
        System.out.println("Price km: ");
        s.add(CLIReader.readline());
        System.out.println("New client discount: ");
        s.add(CLIReader.multiChoiceString(List.of("true","false")));
        System.out.println("Extra price holidays%: ");
        s.add(CLIReader.readline());
        System.out.println("Plant size: ");
        s.add(CLIReader.multiChoiceString(List.of("small","medium","large")));
        System.out.println("Plant type: ");
        s.add(CLIReader.multiChoiceString(List.of("indoor","outdoor")));
        System.out.print("Amount");
        s.add(CLIReader.readline());
        return s;
    }

    public List<String> choiceRequest() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.print("Id request form: ");
        s.add(CLIReader.readline());
        System.out.print("Client username: ");
        s.add(CLIReader.readline());
        System.out.print("Plant: ");
        s.add(CLIReader.readline());
        System.out.print("Start year-month-day: ");
        s.add(CLIReader.readline());
        System.out.print("End year-month-day: ");
        s.add(CLIReader.readline());
        return s;
    }
}
