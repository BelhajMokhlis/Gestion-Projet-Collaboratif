<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light background for contrast */
        }
        h1 {
            color: #343a40; /* Darker text color for headers */
            font-weight: bold; /* Bold header */
        }
        .container {
            margin-top: 30px; /* Add some margin at the top */
            border-radius: 8px; /* Rounded corners for the container */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            background-color: #ffffff; /* White background for the main content */
            padding: 20px; /* Padding inside the container */
        }
        .alert {
            margin-bottom: 20px; /* Space between the alert and other content */
        }
        .table th {
            background-color: #007bff; /* Header background color */
            color: white; /* Header text color */
        }
        .table tbody tr:hover {
            background-color: #f1f1f1; /* Highlight row on hover */
        }
        .pagination .page-item.active .page-link {
            background-color: #007bff; /* Active page link color */
            border-color: #007bff; /* Active border color */
        }
        .btn-info {
            background-color: #17a2b8; /* Info button color */
            border-color: #17a2b8; /* Info button border color */
        }
        .btn-success {
            background-color: #28a745; /* Success button color */
            border-color: #28a745; /* Success button border color */
        }
        .btn-danger {
            background-color: #dc3545; /* Danger button color */
            border-color: #dc3545; /* Danger button border color */
        }
        .btn-warning {
            background-color: #ffc107; /* Warning button color */
            border-color: #ffc107; /* Warning button border color */
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mt-5">Project List</h1>
    <!-- Search Form -->
    <form action="ProjectsServlet" method="get" class="form-inline mb-3">
        <input type="hidden" name="action" value="search">
        <div class="form-group mx-sm-3 mb-2">
            <label for="searchTitle" class="sr-only">Project Name</label>
            <input type="text" class="form-control" id="searchTitle" name="title" placeholder="Search by title">
        </div>
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>

    <!-- Debugging Output -->
    <c:if test="${empty projects}">
        <p>No projects available</p>
    </c:if>

    <!-- Display the feedback message -->
    <c:if test="${not empty sessionScope.msg}">
        <div class="alert alert-info">
            ${sessionScope.msg}
        </div>
    </c:if>

    <!-- Project Table -->
    <table class="table table-striped mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.id}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>${project.startDate}</td>
                <td>${project.endDate}</td>
                <td>${project.status}</td>
                <td>
                    <!-- View Project Button -->
                    <a href="ProjectsServlet?action=view&id=${project.id}" class="btn btn-primary btn-sm">View</a>

                    <!-- View Project Tasks Button -->
                    <form action="${pageContext.request.contextPath}/tasks" method="get" class="d-inline">
                        <input type="hidden" name="action" value="list">
                        <input type="hidden" name="projectID" value="${project.id}">
                        <button type="submit" class="btn btn-info btn-sm">View Tasks</button>
                    </form>

                    <!-- Update Project Button -->
                    <a href="ProjectsServlet?action=edit&id=${project.id}" class="btn btn-warning btn-sm">Update</a>

                    <!-- Delete Project Button -->
                    <form action="ProjectsServlet" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${project.id}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this project?');">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination Controls -->
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                <a class="page-link" href="ProjectsServlet?action=list&page=1">First</a>
            </li>
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="ProjectsServlet?action=list&page=${currentPage - 1}">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                    <a class="page-link" href="ProjectsServlet?action=list&page=${i}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="ProjectsServlet?action=list&page=${currentPage + 1}">Next</a>
                </li>
            </c:if>
            <li class="page-item <c:if test="${currentPage == totalPages}">disabled</c:if>">
                <a class="page-link" href="ProjectsServlet?action=list&page=${totalPages}">Last</a>
            </li>
        </ul>
    </nav>

    <!-- Add New Project Button -->
    <a href="jsp/project/addProject.jsp" class="btn btn-success mt-3">Add New Project</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
