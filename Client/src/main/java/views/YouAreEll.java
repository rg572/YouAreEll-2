package views;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.*;
import models.Id;
import models.Message;

import java.util.ArrayList;


public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;

    public YouAreEll (MessageController m, IdController j) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController());
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        ArrayList<Id> idList = idCtrl.getIds();
        StringBuilder sbuild = new StringBuilder();
        for(Id id : idList){
            sbuild.append(new IdTextView(id).toString()).append("\n");
        }
        return sbuild.toString();
        //return MakeURLCall("/ids", "GET", "");
    }

    public String post_ids(String name, String github){
        Id newID = idCtrl.postId(new Id(name, github));
        if(newID!= null){
            return "Your new id is:\n" + new IdTextView(newID).toString();
        }
        else{
            return "ID posting failed";
        }
    }

    public String get_messages() {

        ArrayList<Message> messageList = msgCtrl.getMessages();
        StringBuilder sbuild = new StringBuilder();
        for(Message message : messageList){
            sbuild.append(new MessageTextView(message).toString()).append("\n");
        }
        return sbuild.toString();
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://zipcode.rocks:8085/ids").asJson();
            //System.out.println(jsonResponse.getBody().toString());
            StringBuilder sbuild = new StringBuilder();
        } catch(UnirestException e){
            e.printStackTrace();
        }
        return "nada";
    }
}
