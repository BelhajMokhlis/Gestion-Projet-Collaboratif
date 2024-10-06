package dao.Interface;

import java.util.List;

import model.Membre;

/**
 * Interface for managing member data in the database.
 */
public interface MemberDao {
    /**
     * Adds a new member to the database.
     *
     * @param membre The member to be added
     * @return true if the member was successfully added, false otherwise
     */
    boolean addMember(Membre membre);

    /**
     * Removes a member from the database.
     *
     * @param membre The member to be removed
     * @return true if the member was successfully removed, false otherwise
     */
    boolean removeMember(Membre membre);

    /**
     * Updates an existing member's information in the database.
     *
     * @param membre The member with updated information
     * @return true if the member was successfully updated, false otherwise
     */
    boolean updateMember(Membre membre);

    /**
     * Retrieves a member from the database by their ID.
     *
     * @param id The ID of the member to retrieve
     * @return The member with the specified ID, or null if not found
     */
    Membre getMember(int id);

    /**
     * Retrieves all members from the database.
     *
     * @return A list of all members in the database
     */
    List<Membre> getAllMembers();

    /**
     * Retrieves all members belonging to a specific team.
     *
     * @param teamId The ID of the team
     * @return A list of members belonging to the specified team
     */
    List<Membre> getMembersByTeam(int teamId);
}
