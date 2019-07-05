package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
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

    private Integer numberOfMessages = 20;

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

        return getNewest(list);
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

    public String postMessage(String myId, String toId, Message msg) {

        String jpayload;
        try {
            jpayload = mapper.writeValueAsString(msg);
        } catch(JsonProcessingException e){
            e.printStackTrace();
            return "Message object could not be JSONified";
        }

        String jsonString = TransactionController.getInstance().postStuff("/ids/:" + myId + "/messages",jpayload);

        try{
            Message message = mapper.readValue(jsonString, Message.class);
            return "Message posted";
        } catch(JsonParseException e){
            return jsonString;
        } catch(IOException e){
            e.printStackTrace();
            return "Unexpected IOException";
        }

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

    public ArrayList<Message> getNewest(ArrayList<Message> list){
        return list.stream().distinct().sorted((m1, m2)->m2.getTimestamp().compareTo(m2.getTimestamp())).
                limit(numberOfMessages).collect(Collectors.toCollection(ArrayList::new));
    }

 
}