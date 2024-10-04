package service;

import java.util.List;

import model.Membre;
import repository.impl.MemberRepositoryImpl;

public class MemberService {
    private MemberRepositoryImpl memberRepositoryImpl;

    public MemberService() {
        this.memberRepositoryImpl = new MemberRepositoryImpl();
    }    

    public boolean addMember(Membre membre) {
        return memberRepositoryImpl.addMember(membre);
    }
    public boolean removeMember(Membre membre) {
        return memberRepositoryImpl.removeMember(membre);
    }
    public boolean updateMember(Membre membre) {
        return  memberRepositoryImpl.updateMember(membre);
    }
    public Membre getMember(int id) {
        return memberRepositoryImpl.getMember(id);
    }
    public List<Membre> getAllMembers() {
        return memberRepositoryImpl.getAllMembers();
    }
    public List<Membre> getMembersByTeam(int teamId) {
        return memberRepositoryImpl.getMembersByTeam(teamId);
    }

}
