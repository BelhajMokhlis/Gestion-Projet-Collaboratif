<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Management Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="text-center mb-4">Project Management Dashboard</h1>
        <div class="row justify-content-center">
            <div class="col-md-4 mb-3">
                <a href="${pageContext.request.contextPath}/teams" class="btn btn-primary btn-lg btn-block">
                    <i class="fas fa-users mr-2"></i> Teams Management
                </a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="${pageContext.request.contextPath}/jsp/project/projects.jsp" class="btn btn-success btn-lg btn-block">
                    <i class="fas fa-project-diagram mr-2"></i> Project Management
                </a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="${pageContext.request.contextPath}/jsp/tasks.jsp" class="btn btn-info btn-lg btn-block">
                    <i class="fas fa-tasks mr-2"></i> Tasks Management
                </a>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
