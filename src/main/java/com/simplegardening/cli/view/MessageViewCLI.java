package com.simplegardening.cli.view;

import com.simplegardening.cli.utils.CLIReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageViewCLI {
    public int getChoice() throws IOException {
        return CLIReader.multiChoice(List.of("send message","see list chat","see chat"));
    }
    public List<String> sendMessage() throws IOException {
        List<String> s = new ArrayList<>();
        System.out.print("Receiver name: ");
        s.add(CLIReader.readline());
        System.out.print("Message: ");
        s.add(CLIReader.readline());
        return s;
    }
    public String getChat() throws IOException {
        System.out.print("Username: ");
        return (CLIReader.readline());
    }


    public void viewChat(List<String> messages){
        for (String mes : messages) System.out.println(mes);
    }

}