package views;

import models.Message;

public class MessageTextView {

    private Message msgToDisplay;

    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;
    }
    @Override public String toString() {
        return "From: " + msgToDisplay.getFromid() + "\nTo:    " + msgToDisplay.getToid() + "\n\"" +
                msgToDisplay.getMessage() + "\"\n";
    } 
}