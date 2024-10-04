package repository.Interface;

import java.util.List;

import model.Team;

public interface TeamRepository {
    boolean addTeam(Team team);
    boolean removeTeam(Team team);
    boolean updateTeam(Team team);
    Team getTeam(int id);
    List<Team> getAllTeams();

}
