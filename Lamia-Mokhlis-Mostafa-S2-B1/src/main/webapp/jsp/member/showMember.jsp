<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Member</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    
</head>
<body>
    <div class="container mt-4">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-light p-2">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp"><i class="fas fa-home"></i> Home</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/teams">Teams List</a></li>
                <li class="breadcrumb-item active" aria-current="page">Member</li>
                
            </ol>
        </nav>
                <h1 class="mb-4">Edit Member</h1>
        
        
         <c:if test="${not empty message}">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="card mb-4">
            <div class="card-body">
                <form action="?action=editMember&id=${membre.id}" method="post" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <label for="firstName" class="form-label">First Name:</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${membre.firstName}" required>
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last Name:</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${membre.lastName}" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" value="${membre.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="role" class="form-label">Role:</label>
                        <select class="form-control" id="role" name="role">
                            <option value="DESIGNER" ${membre.role eq 'DESIGNER' ? 'selected' : ''}>DESIGNER</option>
                            <option value="DEVELOPER" ${membre.role eq 'DEVELOPER' ? 'selected' : ''}>DEVELOPER</option>
                            <option value="PROJECT_MANAGER" ${membre.role eq 'PROJECT_MANAGER' ? 'selected' : ''}>PROJECT_MANAGER</option>
                        </select>
                    </div>
          
                    <button type="submit" class="btn btn-primary" id="updateButton" disabled>Update</button>
                </form>
            </div>
        </div>

        <!-- New Task Table -->
        <h2 class="mb-3">Member Tasks</h2>
        <c:choose>
            <c:when test="${not empty taskmessage}">
                <div class="alert alert-info alert-dismissible fade show" role="alert">
                    ${taskmessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Task ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Priority</th>
                            <th>Status</th>
                            <th>Creation Date</th>
                            <th>Due Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="task" items="${tasks}">
                            <tr>
                                <td>${task.taskID}</td>
                                <td>${task.title}</td>
                                <td>${task.description}</td>
                                <td>${task.priority}</td>
                                <td>${task.status}</td>
                                <td>${task.creationDate}</td>
                                <td>${task.dueDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <script>
    // Store original values
    const originalValues = {
        firstName: "${membre.firstName}",
        lastName: "${membre.lastName}",
        email: "${membre.email}",
        role: "${membre.role}"
    };

    // Function to check if any field has changed
    function checkForChanges() {
        const fields = ['firstName', 'lastName', 'email', 'role'];
        const hasChanges = fields.some(field => 
            document.getElementById(field).value !== originalValues[field]
        );
        document.getElementById('updateButton').disabled = !hasChanges;
    }

    // Add event listeners to all input fields and select element
    document.querySelectorAll('input, select').forEach(element => {
        element.addEventListener('input', checkForChanges);
        element.addEventListener('change', checkForChanges); // For select element
    });

    function validateForm() {
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var email = document.getElementById("email").value;
        var role = document.getElementById("role").value;
        if (firstName === "" || lastName === "" || email === "" || role === "") {
            alert("Please fill in all fields.");
            return false;
        }
        return true;
    }
    </script>
</body>
</html>
