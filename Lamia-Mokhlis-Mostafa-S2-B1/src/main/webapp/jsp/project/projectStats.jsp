<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Statistics</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h2 {
            color: #007bff;
            font-weight: 600;
        }
        .table {
            margin-top: 20px;
        }
        .thead-dark th {
            background-color: #343a40;
            color: #ffffff;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
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
