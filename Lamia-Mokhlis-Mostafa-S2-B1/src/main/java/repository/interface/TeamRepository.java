package repository.Interface;

import java.util.List;

import model.Team;

public interface TeamRepository {
    public boolean addTeam(Team team);
    public boolean removeTeam(Team team);
    public boolean updateTeam(Team team);
    public Team getTeam(int id);
    public List<Team> getAllTeams();
}
