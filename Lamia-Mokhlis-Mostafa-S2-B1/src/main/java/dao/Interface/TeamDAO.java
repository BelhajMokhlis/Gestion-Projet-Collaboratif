package dao.Interface;

import java.util.List;

import model.Team;

public interface TeamDAO {
    boolean addTeam(Team team);
    boolean removeTeam(Team team);
    boolean updateTeam(Team team);
    Team getTeam(int id);
    List<Team> getAllTeams();

}
