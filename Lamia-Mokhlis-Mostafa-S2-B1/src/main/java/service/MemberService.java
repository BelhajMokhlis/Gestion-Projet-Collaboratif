package service;

import java.util.List;

import model.Membre;
import repository.impl.MemberRepositoryImpl;

/**
 * Service class for managing member-related operations.
 */
public class MemberService {
    private MemberRepositoryImpl memberRepositoryImpl;

    /**
     * Constructs a new MemberService with a MemberRepositoryImpl instance.
     */
    public MemberService() {
        this.memberRepositoryImpl = new MemberRepositoryImpl();
    }    

    /**
     * Adds a new member to the repository.
     *
     * @param membre The member to be added
     * @return true if the member was successfully added, false otherwise
     */
    public boolean addMember(Membre membre) {
        return memberRepositoryImpl.addMember(membre);
    }

    /**
     * Removes a member from the repository.
     *
     * @param membre The member to be removed
     * @return true if the member was successfully removed, false otherwise
     */
    public boolean removeMember(Membre membre) {
        return memberRepositoryImpl.removeMember(membre);
    }

    /**
     * Updates an existing member in the repository.
     *
     * @param membre The member to be updated
     * @return true if the member was successfully updated, false otherwise
     */
    public boolean updateMember(Membre membre) {
        return  memberRepositoryImpl.updateMember(membre);
    }

    /**
     * Retrieves a member by their ID.
     *
     * @param id The ID of the member to retrieve
     * @return The member with the specified ID, or null if not found
     */
    public Membre getMember(int id) {
        return memberRepositoryImpl.getMember(id);
    }

    /**
     * Retrieves all members from the repository.
     *
     * @return A list of all members
     */
    public List<Membre> getAllMembers() {
        return memberRepositoryImpl.getAllMembers();
    }

    /**
     * Retrieves all members belonging to a specific team.
     *
     * @param teamId The ID of the team
     * @return A list of members belonging to the specified team
     */
    public List<Membre> getMembersByTeam(int teamId) {
        return memberRepositoryImpl.getMembersByTeam(teamId);
    }

}
