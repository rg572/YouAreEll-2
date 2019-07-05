package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Message;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionController {

    private static TransactionController INSTANCE = new TransactionController();
    private String rootURL = "http://zipcode.rocks:8085";

    private TransactionController() {}

    public String getStuff(String mainurl, String jpayload){

        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.get(rootURL + mainurl).asJson();
            return jsonResponse.getBody().toString();
        } catch(UnirestException e){
            e.printStackTrace();
        }

        return "nada";
    }

    public String postStuff(String mainurl, String jpayload){
        System.out.println("\nPOST PAYLOAD");
        System.out.println(jpayload);
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.post(rootURL + mainurl).body(jpayload).asJson();
            if(jsonResponse.getStatus() == 200){
                return jsonResponse.getBody().toString();
            }
            else{
                return jsonResponse.getStatusText();
            }
        } catch(UnirestException e){
            e.printStackTrace();
            return "";
        }

    }

    public String putStuff(String mainurl, String jpayload){
        System.out.println("\nPAYLOAD");
        System.out.println(jpayload);
        try{
            HttpResponse<JsonNode> jsonResponse = Unirest.put(rootURL + mainurl).body(jpayload).asJson();
            if(jsonResponse.getStatus()==200){
                return jsonResponse.getBody().toString();
            }
            else{
                System.out.println("RESPONSE BODY");
                System.out.println(jsonResponse.getBody().toString());
                return jsonResponse.getStatusText();
            }
        } catch(UnirestException e){
            e.printStackTrace();
            return "";
        }
    }

    public static TransactionController getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        doShit();
    }

    public static void doShit(){
        ObjectMapper mapper = new ObjectMapper();

        Message message = new Message("You must be destroyed", "Cato", "Carthage");
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(message);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println("JSONSTRING: " + jsonString);

        String responseString = "";
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://zipcode.rocks:8085/ids/:Cato/messages")
                    .body(jsonString).asJson();
            responseString = jsonResponse.getBody().toString();
        }catch(UnirestException e) {
            e.printStackTrace();
        }

        System.out.println("RESPONSE: " + responseString);
    }


}
