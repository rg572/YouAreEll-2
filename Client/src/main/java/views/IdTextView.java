package views;

import models.Id;

public class IdTextView {

    Id id;
    public IdTextView(Id idToDisplay) {
        this.id = idToDisplay;

    }
    @Override public String toString() {
        return("name: " + id.getName() + "\ngithub: " + id.getGithub() + "\nid: " + id.getUserid() + "\n");
    } 
}