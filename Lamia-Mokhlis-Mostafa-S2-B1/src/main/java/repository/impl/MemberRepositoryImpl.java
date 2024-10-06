package repository.impl;

import java.util.List;

import dao.Interface.MemberDao;
import dao.impl.MemberDaoImpl;
import model.Membre;
import repository.Interface.MemberRepository;

/**
 * Implementation of the MemberRepository interface.
 * This class provides methods to interact with member data in the repository.
 */
public class MemberRepositoryImpl implements MemberRepository {
    
    private MemberDao memberDao;

    /**
     * Constructor for MemberRepositoryImpl.
     * Initializes the memberDao with a new instance of MemberDaoImpl.
     */
    public MemberRepositoryImpl() {
        this.memberDao = new MemberDaoImpl();
    }

    /**
     * Adds a new member to the repository.
     *
     * @param membre The member to be added.
     * @return true if the member was successfully added, false otherwise.
     */
    @Override
    public boolean addMember(Membre membre) {
        return memberDao.addMember(membre);
    }

    /**
     * Removes a member from the repository.
     *
     * @param membre The member to be removed.
     * @return true if the member was successfully removed, false otherwise.
     */
    @Override
    public boolean removeMember(Membre membre) {
        return memberDao.removeMember(membre);
    }

    /**
     * Updates an existing member in the repository.
     *
     * @param membre The member to be updated.
     * @return true if the member was successfully updated, false otherwise.
     */
    @Override
    public boolean updateMember(Membre membre) {
        return memberDao.updateMember(membre);
    }

    /**
     * Retrieves a member from the repository by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member with the specified ID, or null if not found.
     */
    @Override
    public Membre getMember(int id) {
        return memberDao.getMember(id);
    }

    /**
     * Retrieves all members from the repository.
     *
     * @return A list of all members in the repository.
     */
    @Override
    public List<Membre> getAllMembers() {
        return memberDao.getAllMembers();
    }

    /**
     * Retrieves all members belonging to a specific team.
     *
     * @param teamId The ID of the team.
     * @return A list of members belonging to the specified team.
     */
    @Override
    public List<Membre> getMembersByTeam(int teamId) {
        return memberDao.getMembersByTeam(teamId);
    }
}