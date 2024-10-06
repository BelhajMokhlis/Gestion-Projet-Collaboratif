package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Team;
import model.Task;
import model.enums.MemberRole;
import model.Membre;
import service.MemberService;
import service.TaskService;
import service.TeamService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsible for handling team-related operations.
 */
public class TeamServlet extends HttpServlet {
    private TeamService teamService;
    private MemberService memberService;
    private TaskService taskService;

    /**
     * Constructor for TeamServlet.
     * Initializes the TeamService.
     */
    public TeamServlet() {
        this.teamService = new TeamService();
        this.memberService = new MemberService();
        this.taskService = new TaskService();
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
        switch (action == null ? "getAllTeams" : action) {
            case "getAllTeams":
                getAllTeams(request, response);
                break;
            case "team":
                getTeam(request, response);
                break;
            case "viewMember":
                showMember(request, response);
                break;
            default:
                // Handle unexpected action or send an error response
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
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
            case "addMember":
                addMember(request, response);
                break;
            case "deleteMember":
                deleteMember(request, response);
                break;
            case "editMember":
                editMember(request, response);
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
        if (update) {
            request.setAttribute("message", "Team updated successfully");
        } else {
            request.setAttribute("message", "Failed to update team");
        }
        getAllTeams(request, response);
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

    /**
     * Adds a new member to a team.
     *
     * @param request  The HTTP request containing member and team data
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void addMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Membre membre = new Membre();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        MemberRole role = MemberRole.valueOf(request.getParameter("role"));
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String teamName = request.getParameter("teamName");
        Team team = new Team();
        team.setId(teamId);
        team.setName(teamName);
        membre.setFirstName(firstName);
        membre.setLastName(lastName);
        membre.setEmail(email);
        membre.setRole(role);
        membre.setTeam(team);
        boolean add = memberService.addMember(membre);
        if (add) {
            request.setAttribute("message", "Member added successfully");
        } else {
            request.setAttribute("message", "Member not added");
        }
        response.sendRedirect(request.getContextPath() + "/teams?action=team&id=" + teamId);
    }

    /**
     * Deletes a member from a team.
     *
     * @param request  The HTTP request containing the member ID and team ID
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("memberId"));
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        Membre membre = new Membre();
        membre.setId(id);
        boolean delete = memberService.removeMember(membre);
        if (delete) {
            request.setAttribute("message", "Member deleted successfully");
        }else{
            request.setAttribute("message", "Member not deleted");
        }
        response.sendRedirect(request.getContextPath() + "/teams?action=team&id=" + teamId);

    }

    /**
     * Displays details of a specific member.
     *
     * @param request  The HTTP request containing the member ID
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void showMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = memberService.getMember(id);
        
        if (membre != null) {
            request.setAttribute("membre", membre);
            
            List<Task> tasks = taskService.getTaskByMemberId(id);
            if (tasks != null && !tasks.isEmpty()) {
                request.setAttribute("tasks", tasks);
            } else {
                request.setAttribute("taskmessage", "No tasks found for this member");
            }
            
            request.getRequestDispatcher("/jsp/member/showMember.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
        }
    }

    /**
     * Edits an existing member's information.
     *
     * @param request  The HTTP request containing updated member data
     * @param response The HTTP response
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException      If an I/O error occurs
     */
    public void editMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // first get the member by id
        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = memberService.getMember(id);
        // then update the member
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        MemberRole role = MemberRole.valueOf(request.getParameter("role"));
        membre.setFirstName(firstName);
        membre.setLastName(lastName);
        membre.setEmail(email);
        membre.setRole(role);
        boolean update = memberService.updateMember(membre);
        if (update) {
            request.setAttribute("message", "Member updated successfully");
        } else {
            request.setAttribute("message", "Member not updated");
        }
        response.sendRedirect(request.getContextPath() + "/teams?action=viewMember&id=" + id);
    }
}
