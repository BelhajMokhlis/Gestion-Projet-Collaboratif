<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Project" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    Project project = (Project) request.getAttribute("project");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="path/to/your/css/styles.css"> <!-- Link your CSS file if needed -->
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Project Details</h1>

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Project ID: <%= project.getId() %></h5>
                <p class="card-text"><strong>Name:</strong> <%= project.getName() %></p>
                <p class="card-text"><strong>Description:</strong> <%= project.getDescription() %></p>
                <p class="card-text"><strong>Start Date:</strong> <%= project.getStartDate().format(dateFormatter) %></p>
                <p class="card-text"><strong>End Date:</strong> <%= project.getEndDate().format(dateFormatter) %></p>
                <p class="card-text"><strong>Status:</strong> <%= project.getStatus() %></p>

                <div class="mt-4">
                    <a href="ProjectsServlet?action=list" class="btn btn-secondary">Back to Projects</a>
                    
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
