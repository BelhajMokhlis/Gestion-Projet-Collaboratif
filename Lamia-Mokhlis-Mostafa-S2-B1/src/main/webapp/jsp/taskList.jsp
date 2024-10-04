<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Tasks</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<body>
    <div class="container">
        <h1>Project Tasks</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>Task title</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Due Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${task.taskID}</td>
                        <td>${task.title}</td>
                        <td>${task.description}</td>
                        <td>${task.status}</td>
                        <td>${task.dueDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation">
    <ul class="pagination mx-auto">
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${currentPage - 1}&size=3">Previous</a>
            </li>
        </c:if>
        
        <c:forEach begin="1" end="${totalPages}" var="i">
            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${i}&size=3">${i}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${currentPage + 1}&size=3">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<!-- Bootstrap JS dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>