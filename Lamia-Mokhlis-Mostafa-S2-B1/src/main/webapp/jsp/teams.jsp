<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Teams List</title>
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4">Teams List</h1>
        
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <td>${team.id}</td>
                        <td>
                            <form action="?action=update" method="post" id="form-${team.id}" style="display:inline;">
                                <input type="hidden" name="id" value="${team.id}">
                                <input type="text" name="Tname" value="${team.name}" class="form-control form-control-sm team-name-input" data-original-value="${team.name}">
                            </form>
                        </td>
                        <td>
                            <button type="submit" form="form-${team.id}" class="btn btn-sm btn-success save-edit-btn" disabled>Save Edit</button>
                            <a href="?action=team&id=${team.id}" class="btn btn-sm btn-info">View</a>
                            <form action="?action=delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${team.id}">
                                <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this team?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <!-- Pagination -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${currentPage - 1}" tabindex="-1">Previous</a>
                </li>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                </li>
            </ul>
        </nav>
        
        <h2 class="mt-4">Add New Team</h2>
        <form action="?action=add" method="post" class="mb-4">
            <div class="form-group">
                <label for="Tname" class="font-weight-bold">Team Name:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-users"></i></span>
                    </div>
                    <input type="text" id="Tname" name="Tname" class="form-control" required placeholder="Enter team name">
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block">
                <i class="fas fa-plus-circle mr-2"></i>Add Team
            </button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function confirmDelete(form) {
            if (confirm('Are you sure you want to delete this team?')) {
                form.submit();
            }
            return false;
        }

        document.addEventListener('DOMContentLoaded', function() {
            const teamNameInputs = document.querySelectorAll('.team-name-input');
            
            teamNameInputs.forEach(input => {
                input.addEventListener('input', function() {
                    const saveButton = this.closest('tr').querySelector('.save-edit-btn');
                    if (this.value !== this.dataset.originalValue) {
                        saveButton.disabled = false;
                    } else {
                        saveButton.disabled = true;
                    }
                });
            });
        });
    </script>
</body>
</html>