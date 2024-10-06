package model;

import java.util.List;

/**
 * Represents a team in the system.
 */
public class Team {
    private int id;
    private String name;
    private List<Membre> members;

    /**
     * Constructs a new Team with the specified id, name, and members.
     *
     * @param id      The unique identifier for the team.
     * @param name    The name of the team.
     * @param members The list of members in the team.
     */
    public Team(int id, String name, List<Membre> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    /**
     * Constructs a new Team with the specified id and name.
     *
     * @param id   The unique identifier for the team.
     * @param name The name of the team.
     */
    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructs a new Team with default values.
     */
    public Team() {}

    /**
     * Gets the team's unique identifier.
     *
     * @return The team's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the team's unique identifier.
     *
     * @param id The new id for the team.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the team's name.
     *
     * @return The team's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the team's name.
     *
     * @param name The new name for the team.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of members in the team.
     *
     * @return The list of members in the team.
     */
    public List<Membre> getMembers() {
        return members;
    }

    /**
     * Sets the list of members in the team.
     *
     * @param members The new list of members for the team.
     */
    public void setMembers(List<Membre> members) {
        this.members = members;
    }
}
