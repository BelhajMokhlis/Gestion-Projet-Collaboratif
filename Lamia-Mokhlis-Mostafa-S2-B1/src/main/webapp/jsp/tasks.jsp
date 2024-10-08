<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tasks</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    
</head>
<body>
<div class="container mt-5">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp"><i class="fas fa-home"></i> Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">New Task</li>
            </ol>
        </nav>

    <h2 class="mb-4">Add New Task</h2>
    <form action="${pageContext.request.contextPath}/tasks" method="post">
        <input type="hidden" name="action" value="insert">

        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" name="title" id="title" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" name="description" id="description" required>
        </div>

        <div class="form-group">
            <label for="priority">Priority:</label>
            <select class="form-control" name="priority" id="priority" required>
                <option value="LOW">Low</option>
                <option value="MEDIUM">Medium</option>
                <option value="HIGH">High</option>
            </select>
        </div>

        <div class="form-group">
            <label for="dueDate">Due Date (MM/DD/YYYY):</label>
            <input type="text" class="form-control" name="dueDate" id="dueDate" placeholder="MM/DD/YYYY" required>
        </div>

        <button type="submit" class="btn btn-primary">Add New Task!</button>
    </form>
</div>

<form action="${pageContext.request.contextPath}/tasks" method="get">
    <input type="hidden" name="action" value="list">
    <input type="hidden" name="projectID" value="6"> <!-- static project ID for now -->
    <button type="submit" class="btn btn-primary">View Project Tasks</button>
</form>

<!-- Bootstrap JS dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
