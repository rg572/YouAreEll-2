package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import models.Id;

public class IdController {
    Id myId = new Id("Marcus P. Cato", "CatoMajor","d2ba40338b38d576d5706fb415c43c71ff4da28a");

    private ObjectMapper mapper = new ObjectMapper();


    public ArrayList<Id> getIds() {
        String jsonString = TransactionController.getInstance().getStuff("/ids","");
        TypeFactory typeFactory = mapper.getTypeFactory();
        ArrayList<Id> list = new ArrayList<>();
        try {
            list = mapper.readValue(jsonString, typeFactory.constructCollectionType(ArrayList.class, Id.class));
        } catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    public Id postId(Id id) {
        String jsonpayload = "";
        try {
            jsonpayload = mapper.writeValueAsString(id);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        String jsonString = TransactionController.getInstance().postStuff("/ids",jsonpayload);
        try{
            Id newId = mapper.readValue(jsonString,Id.class);
            this.myId = newId;
            return newId;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public Id putId(Id id) {
        String jsonpayload;
        try{
            jsonpayload = mapper.writeValueAsString(id);
        } catch(JsonProcessingException e){
            e.printStackTrace();
            return null;
        }

        String jsonString = TransactionController.getInstance().putStuff("/ids",jsonpayload);
        System.out.println("JSONSTRING " + jsonString);
        try{
            Id updatedId = mapper.readValue(jsonString, Id.class);
            this.myId = updatedId;
            return updatedId;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getGithub(){
        return myId.getGithub();
    }

    public Id getMyId(){
        return myId;
    }

    public static void main(String[] args) {
        IdController idController = new IdController();
        idController.getIds();
    }
 
}