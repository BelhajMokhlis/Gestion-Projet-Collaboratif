package model;

import java.util.List;

public class Team {
    private int id;
    private String name;
    private List<Membre> members;

    public Team(int id, String name, List<Membre> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }
    public Team(int id ,String name) {
        this.id = id;
        this.name = name;
    }
    public Team() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Membre> getMembers() {
        return members;
    }
    public void setMembers(List<Membre> members) {
        this.members = members;
    }
    
    
}
