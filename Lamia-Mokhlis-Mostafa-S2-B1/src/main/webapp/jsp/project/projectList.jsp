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
    <a href="ProjectsServlet?action=create" class="btn btn-success mt-3">Add New Project</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
