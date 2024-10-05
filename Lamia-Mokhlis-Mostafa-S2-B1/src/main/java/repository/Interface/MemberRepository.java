package repository.Interface;

import java.util.List;

import model.Membre;

public interface MemberRepository {

    boolean addMember(Membre membre);
    boolean removeMember(Membre membre);
    boolean updateMember(Membre membre);
    Membre getMember(int id);
    List<Membre> getAllMembers();
    List<Membre> getMembersByTeam(int teamId);  

}
