<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>

<html>
<head>
    <title>Add New Project</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Add New Project</h1>
    <form action="${pageContext.request.contextPath}/ProjectsServlet?action=add" method="post">
        <div class="form-group">
            <label for="name">Project Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>
        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="date" class="form-control" id="startDate" name="startDate" 
                value="<%= LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) %>" required>
        </div>
        <div class="form-group">
            <label for="endDate">End Date</label>
            <input type="date" class="form-control" id="endDate" name="endDate">
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status" required>
                <option value="IN_PREPARATION">In Preparation</option>
                <option value="IN_PROGRESS">In Progress</option>
                <option value="ON_HOLD">On Hold</option>
                <option value="COMPLETED">Completed</option>
                <option value="CANCELED">Canceled</option>
            </select>
        </div>
          <!-- Team ID Input -->
        <div class="form-group">
            <label for="teamId">Team:</label>
            <select class="form-control" id="teamId" name="teamId" required>
                <c:forEach var="team" items="${teams}">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Add Project</button>
        <a href="ProjectsServlet?action=list" class="btn btn-secondary">Back to Project List</a>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
