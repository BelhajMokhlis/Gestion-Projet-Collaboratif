package model;

import model.enums.MemberRole;

/**
 * Represents a member in the system.
 */
public class Membre {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private MemberRole role;
    private Team team;

    /**
     * Constructs a new Membre with the specified details.
     *
     * @param id        The unique identifier for the member.
     * @param firstName The first name of the member.
     * @param lastName  The last name of the member.
     * @param email     The email address of the member.
     * @param role      The role of the member.
     * @param team      The team to which the member belongs.
     */
    public Membre(int id, String firstName, String lastName, String email, MemberRole role, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.team = team;
    }
    
    /**
     * Constructs a new Membre with no initial values.
     */
    public Membre() {}

    /**
     * Gets the unique identifier of the member.
     *
     * @return The member's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the member.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the member.
     *
     * @return The member's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the member.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the member.
     *
     * @return The member's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the member.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the member.
     *
     * @return The member's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the member.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the member.
     *
     * @return The member's role.
     */
    public MemberRole getRole() {
        return role;
    }

    /**
     * Sets the role of the member.
     *
     * @param role The role to set.
     */
    public void setRole(MemberRole role) {
        this.role = role;
    }

    /**
     * Gets the team to which the member belongs.
     *
     * @return The member's team.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team to which the member belongs.
     *
     * @param team The team to set.
     */
    public void setTeam(Team team) {
        this.team = team;
    }   

}
