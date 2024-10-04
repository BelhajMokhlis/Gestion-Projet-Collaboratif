package service;

import repository.impl.TeamRepositoryImpl;
import model.Team;

import java.util.List;

public class TeamService {
    private TeamRepositoryImpl teamRepositoryImpl;

    public TeamService() {
        this.teamRepositoryImpl = new TeamRepositoryImpl();
    }
    public boolean addTeam(Team team) {
        return teamRepositoryImpl.addTeam(team);
    }
    public boolean removeTeam(Team team) {
        return teamRepositoryImpl.removeTeam(team);
    }
    public boolean updateTeam(Team team) {
        return teamRepositoryImpl.updateTeam(team);
    }
    public Team getTeam(int id) {
        return teamRepositoryImpl.getTeam(id);
    }
    public List<Team> getAllTeams() {
        return teamRepositoryImpl.getAllTeams();
    }
}
