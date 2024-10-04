package repository.impl;

import java.util.List;

import dao.Interface.MemberDao;
import dao.impl.MemberDaoImpl;
import model.Membre;
import repository.Interface.MemberRepository;

public class MemberRepositoryImpl implements MemberRepository {
    
    private MemberDao memberDao;

    public MemberRepositoryImpl() {
        this.memberDao = new MemberDaoImpl();
    }

    @Override
    public boolean addMember(Membre membre) {
        return memberDao.addMember(membre);
    }

    @Override
    public boolean removeMember(Membre membre) {
        return memberDao.removeMember(membre);
    }

    @Override
    public boolean updateMember(Membre membre) {
        return memberDao.updateMember(membre);
    }

    @Override
    public Membre getMember(int id) {
        return memberDao.getMember(id);
    }

    @Override
    public List<Membre> getAllMembers() {
        return memberDao.getAllMembers();
    }

    @Override
    public List<Membre> getMembersByTeam(int teamId) {
        return memberDao.getMembersByTeam(teamId);
    }
   
    
    

    
}


