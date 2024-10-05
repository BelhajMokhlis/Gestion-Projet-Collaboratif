<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Project</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Update Project</h1>

    <!-- Display validation errors or success messages -->
    <c:if test="${not empty msg}">
        <div class="alert alert-info">
            ${msg}
        </div>
    </c:if>

    <!-- Update Project Form -->
    <form action="ProjectsServlet?action=update" method="post">
        <input type="hidden" name="action" value="update"> <!-- Hidden field to trigger update action -->
        <input type="hidden" name="id" value="${project.id}"> <!-- Hidden field for project ID -->

        <div class="form-group">
            <label for="name">Project Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${project.name}" required>
        </div>

        <div class="form-group">
            <label for="description">Project Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${project.description}</textarea>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" class="form-control" id="startDate" name="startDate" value="${project.startDate}" required>
        </div>

        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" class="form-control" id="endDate" name="endDate" value="${project.endDate}" required>
        </div>

       <div class="form-group">
    <label for="status">Status:</label>
    <select class="form-control" id="status" name="status" required>
        <option value="IN_PREPARATION" ${project.status == 'IN_PREPARATION' ? 'selected' : ''}>In Preparation</option>
        <option value="IN_PROGRESS" ${project.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
        <option value="ON_HOLD" ${project.status == 'ON_HOLD' ? 'selected' : ''}>On Hold</option>
        <option value="COMPLETED" ${project.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
        <option value="CANCELED" ${project.status == 'CANCELED' ? 'selected' : ''}>Canceled</option>
    </select>
</div>
  <!-- Team ID Input -->
        <div class="form-group">
            <label for="teamId">Team ID:</label>
            <input type="number" class="form-control" id="teamId" name="teamId" value="${project.teamId}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Project</button>
        <a href="ProjectsServlet?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
