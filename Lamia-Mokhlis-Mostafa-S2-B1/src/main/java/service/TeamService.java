package service;

import repository.impl.TeamRepositoryImpl;
import model.Team;

import java.util.List;

public class TeamService {
    private TeamRepositoryImpl teamRepositoryImpl;

    public TeamService() {
        this.teamRepositoryImpl = new TeamRepositoryImpl();
    }
    public void addTeam(Team team) {
        teamRepositoryImpl.addTeam(team);
    }
    public void removeTeam(Team team) {
        teamRepositoryImpl.removeTeam(team);
    }
    public void updateTeam(Team team) {
        teamRepositoryImpl.updateTeam(team);
    }
    public Team getTeam(int id) {
        return teamRepositoryImpl.getTeam(id);
    }
    public List<Team> getAllTeams() {
        return teamRepositoryImpl.getAllTeams();
    }
}
