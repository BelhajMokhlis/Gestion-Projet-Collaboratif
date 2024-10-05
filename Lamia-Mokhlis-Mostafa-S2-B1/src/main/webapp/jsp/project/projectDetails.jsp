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
    <style>
    body {
        background-color: #f8f9fa;
    }
    .card {
        border: none;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .card-title {
        font-size: 1.5rem;
        color: #343a40;
    }
    .card-text {
        font-size: 1.1rem;
        color: #6c757d;
    }
    .btn-secondary {
        background-color: #007bff;
        border-color: #007bff;
    }
    .btn-secondary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    h1 {
        font-weight: bold;
        color: #007bff;
    }
</style>

</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4 text-center">Project Details</h1>

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Project ID: <%= project.getId() %></h5>
                <p class="card-text"><strong>Name:</strong> <%= project.getName() %></p>
                <p class="card-text"><strong>Description:</strong> <%= project.getDescription() %></p>
                <p class="card-text"><strong>Start Date:</strong> <%= project.getStartDate().format(dateFormatter) %></p>
                <p class="card-text"><strong>End Date:</strong> <%= project.getEndDate().format(dateFormatter) %></p>
                <p class="card-text"><strong>Status:</strong> <%= project.getStatus() %></p>

                <div class="mt-4 text-center">
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
