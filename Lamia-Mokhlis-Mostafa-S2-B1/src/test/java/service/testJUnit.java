package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.impl.TeamDAOImpl;
import model.Team;

class testJUnit {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Test
	void testAddTeam() {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		Team team = new Team(17 , "Team A");
		assertTrue(teamDAO.addTeam(team));
	}

	@Test
	void testGetAllTeams() {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		Team team = new Team(17 , "Team A");
		assertEquals(teamDAO.getAllTeams(), team);
	}
	@Test
	void testRemoveTeam() {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		Team team = new Team(17 , "Team A");
		assertTrue(teamDAO.removeTeam(team));
	}
}
