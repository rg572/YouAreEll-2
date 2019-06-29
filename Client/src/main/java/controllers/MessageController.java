package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Id;
import models.Message;

public class MessageController {

    private ObjectMapper mapper = new ObjectMapper();

    private HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        String jsonString = TransactionController.getInstance().getStuff("/messages","");
        TypeFactory typeFactory = mapper.getTypeFactory();
        ArrayList<Message> list = new ArrayList<>();
        try {
            list = mapper.readValue(jsonString, typeFactory.constructCollectionType(ArrayList.class, Message.class));
        } catch (IOException e){
            e.printStackTrace();
        }

        messagesSeen.addAll(list);

        return list;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }

    public static void main(String[] args) {
        MessageController msgCtrl = new MessageController();
        msgCtrl.doShit();

    }

    public void doShit(){
        Message message = new Message("You must be destroyed","Cato","Carthage");
        String jsonString ="";
        try {
            jsonString = mapper.writeValueAsString(message);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }



        System.out.println(jsonString);


    }

 
}