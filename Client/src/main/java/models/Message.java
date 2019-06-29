package models;

import java.util.Objects;

/*
 * POJO for an Message object
 */
public class Message {

    private String message;
    private String fromid;
    private String toid;
    private String sequence;
    private String timestamp;

    public Message(){

    }

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromid = fromId;
        this.toid = toId;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toId) {
        this.toid = toId;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromId) {
        this.fromid = fromId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}