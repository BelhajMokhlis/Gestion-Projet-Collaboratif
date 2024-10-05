<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Projects</title>
    <!-- Bootstrap CSS for styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Project Management</h1>
    
    <!-- Link styled as a button to View Projects -->
    <a href="${pageContext.request.contextPath}/ProjectsServlet?action=list" class="btn btn-primary mt-3">View Projects</a>
<a href="${pageContext.request.contextPath}/ProjectsServlet?action=stats" class="btn btn-info mt-3">View Project Statistics</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
