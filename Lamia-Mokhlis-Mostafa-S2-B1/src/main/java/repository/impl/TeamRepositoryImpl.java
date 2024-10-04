package repository.impl;

import dao.TeamDAOImpl;
import model.Team;

import java.util.List;

import dao.TeamDAO;

import repository.Interface.TeamRepository;
public class TeamRepositoryImpl implements TeamRepository {
    private TeamDAO teamDAOImpl;

    public TeamRepositoryImpl() {
        this.teamDAOImpl = new TeamDAOImpl();
    }

    @Override
    public boolean addTeam(Team team) {
        return teamDAOImpl.addTeam(team);
    }

    @Override
    public boolean removeTeam(Team team) {
        return teamDAOImpl.removeTeam(team);
    }

    @Override
    public boolean updateTeam(Team team) {
        return teamDAOImpl.updateTeam(team);
    }
    
    @Override
    public Team getTeam(int id) {
        return teamDAOImpl.getTeam(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDAOImpl.getAllTeams();
    }
}
