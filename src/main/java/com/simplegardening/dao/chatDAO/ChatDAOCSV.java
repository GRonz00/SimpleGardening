package com.simplegardening.dao.chatDAO;


import com.simplegardening.model.Session;
import com.simplegardening.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOCSV implements ChatDAO{


    @Override
    public void saveMessage(String message, Session session, User receiver) {

        try {
            File file = new File(System.getenv("PATH_FILE"));

            FileOutputStream fos = new FileOutputStream (file, true);
            PrintWriter pw = new PrintWriter (fos);
            pw.println (receiver.getUsername()+" "+message);
            pw.close ();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<String> getMessages(Session session, User receiver) throws Exception {
        List<String> messages = new ArrayList<>();
        BufferedReader in;
        try {
            File file = new File(System.getenv("PATH_FILE"));
            in = new BufferedReader( new FileReader(file));
        String message;
        while (( message = in.readLine()) != null){
            if(message.contains(session.getUser().getUsername())
                    && message.contains(receiver.getUsername())) {
                String s2 = message.replaceFirst(session.getUser().getUsername(),"");
                messages.add(s2);
            }
        }
        in.close();
        } catch (IOException e) {
            throw new Exception("File not found");
        }
        return messages;
    }




}
