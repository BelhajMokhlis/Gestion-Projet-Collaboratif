package dao.Interface;

import java.util.List;

import model.Membre;

public interface MemberDao {
    boolean addMember(Membre membre);
    boolean removeMember(Membre membre);
    boolean updateMember(Membre membre);
    Membre getMember(int id);
    List<Membre> getAllMembers();
    List<Membre> getMembersByTeam(int teamId);

}
