<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Statistics</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Project Statistics</h2>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Project Name</th>
                <th scope="col">Task Count</th>
                <th>Member Count</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.name}</td>
                    <td>${taskCounts[project.id] != null ? taskCounts[project.id] : 0}</td> 
                  	<td>${memberCounts[project.id] != null ? memberCounts[project.id] : 0}</td> 
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Back Button -->
    <a href="${pageContext.request.contextPath}/ProjectsServlet?action=list" class="btn btn-primary mt-3">Back to Project List</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
