package models;

/* 
 * POJO for an Id object
 */
public class Id {

    private String userid;
    private String name;
    private String github;

    public Id(){
    }
    
    public Id (String name, String githubId) {
        this.name = name;
        this.github = githubId;
    }

    public Id(String name, String github, String userid){
        this.name = name;
        this.github = github;
        this.userid = userid;
    }


    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}