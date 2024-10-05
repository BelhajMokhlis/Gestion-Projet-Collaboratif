<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Member</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Edit Member</h1>
        
         <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
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
