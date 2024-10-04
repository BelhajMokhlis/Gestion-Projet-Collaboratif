package service;

import model.Membre;
import repository.impl.MemberRepositoryImpl;

public class MemberService {
    private MemberRepositoryImpl memberRepositoryImpl;

    public MemberService() {
        this.memberRepositoryImpl = new MemberRepositoryImpl();
    }    

    public void addMember(Membre membre) {
        memberRepositoryImpl.addMember(membre);
    }
    public void removeMember(Membre membre) {
        memberRepositoryImpl.removeMember(membre);
    }
    public void updateMember(Membre membre) {
        memberRepositoryImpl.updateMember(membre);
    }
    public void getMember(int id) {
        memberRepositoryImpl.getMember(id);
    }
    public void getAllMembers() {
        memberRepositoryImpl.getAllMembers();
    }
    public void getMembersByTeam(int teamId) {
        memberRepositoryImpl.getMembersByTeam(teamId);
    }

}
