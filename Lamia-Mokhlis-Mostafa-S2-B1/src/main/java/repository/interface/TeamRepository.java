package repository.Interface;

<<<<<<< HEAD
public interface TeamRepository {
=======
import java.util.List;
>>>>>>> 430bc549d023f63a77db17f9b18d7d3f023c9583

import model.Team;

public interface TeamRepository {
    public boolean addTeam(Team team);
    public boolean removeTeam(Team team);
    public boolean updateTeam(Team team);
    public Team getTeam(int id);
    public List<Team> getAllTeams();
}
