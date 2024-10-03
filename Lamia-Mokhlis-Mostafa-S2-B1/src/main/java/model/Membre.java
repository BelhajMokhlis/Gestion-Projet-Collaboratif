package model;

import model.enums.MemberRole;

public class Membre {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private MemberRole role;
    private Team team;

    public Membre(int id, String firstName, String lastName, String email, MemberRole role, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.team = team;
    }
    
    public Membre() {}

 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }                   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public MemberRole getRole() {
        return role;
    }
    public void setRole(MemberRole role) {
        this.role = role;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }   

    
    
}
