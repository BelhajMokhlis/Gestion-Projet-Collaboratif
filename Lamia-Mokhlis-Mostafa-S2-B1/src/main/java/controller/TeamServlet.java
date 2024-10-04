package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Team;
import model.Membre;
import repository.impl.MemberRepositoryImpl;
import service.MemberService;
import service.TeamService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsible for handling team-related operations.
 */
public class TeamServlet extends HttpServlet {
    private TeamService teamService;
    private MemberService memberService;

    /**
     * Constructor for TeamServlet.
     * Initializes the TeamService.
     */
    public TeamServlet() {
        this.teamService = new TeamService();
        this.memberService = new MemberService();
    }

    /**
     * Handles GET requests for team operations.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
      if (action == null) {
        getAllTeams(request, response);
      } else if (action.equals("team")) {
        getTeam(request, response);
      }
             
    }

    /**
     * Handles POST requests for team operations.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addTeam(request, response);
                break;
            case "update":
                updateTeam(request, response);
                break;
            case "delete":
                deleteTeam(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    // create team
    /**
     * Adds a new team.
     *
     * @param request  The HTTP request containing team data
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Tname");
        Team team = new Team();
        team.setName(name);

        boolean add = teamService.addTeam(team);
        if (add) {
        	request.setAttribute("message", "Team added successfully");
            getAllTeams(request, response);
        } else {
            forwardToTeamsPage(request, response, "Team not added");
        }
    }

    // update team
    /**
     * Updates an existing team.
     *
     * @param request  The HTTP request containing updated team data
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void updateTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("Tname");
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        boolean update = teamService.updateTeam(team);
        forwardToTeamsPage(request, response, update ? "Team updated successfully" : "Team not updated");
    }

    // delete team
    /**
     * Deletes a team.
     *
     * @param request  The HTTP request containing the team ID to delete
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Team team = new Team();
        team.setId(id);
        boolean delete = teamService.removeTeam(team);
        if (delete) {
            request.setAttribute("message", "Team deleted successfully");
            getAllTeams(request, response);
        } else {
            request.setAttribute( "message", "Team not deleted");
            getAllTeams(request, response);
        }
    }

    // get team
    /**
     * Retrieves a specific team by ID.
     *
     * @param request  The HTTP request containing the team ID
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void getTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
      
            Team team = teamService.getTeam(id);
            if (team != null) {
                request.setAttribute("team", team);
                List<Membre> members = memberService.getMembersByTeam(team.getId());
                request.setAttribute("members",members);
                
                request.getRequestDispatcher("/jsp/team/team.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Team not found  ");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid team ID");
        }
    }

    // get all teams
    /**
     * Retrieves all teams.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void getAllTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = teamService.getAllTeams();
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("jsp/teams.jsp").forward(request, response);
    }

    /**
     * Forwards the request to the teams page with a message.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @param message  The message to be displayed on the teams page
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    private void forwardToTeamsPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/jsp/teams.jsp").forward(request, response);
    }





    
    
}
