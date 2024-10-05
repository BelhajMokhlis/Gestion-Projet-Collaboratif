<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Team Information</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
    <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
      <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp"><i class="fas fa-home"></i> Home</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/teams">Teams List</a></li>
                <li class="breadcrumb-item active" aria-current="page">${team.name}</li>
                
            </ol>
        </nav>
        <h1 class="mb-4 text-center">${team.name}</h1>
        
        <c:if test="${not empty members}">
            <div class="table-responsive">
                <table class="table table-hover" id="membersTable">
                    <thead class="thead-dark">
                        <tr>
                            <th>firstName</th>
                            <th>lastName</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td>${member.firstName} </td>
                                <td>${member.lastName}</td>
                                <td>${member.email}</td>
                                <td>${member.role }</td>
                                <td>
                                    <a href="?action=viewMember&id=${member.id}" class="btn btn-sm btn-outline-primary mr-2">
                                        <i class="fas fa-eye"></i> View
                                    </a>
                                    <form action="?action=deleteMember" method="post" style="display:inline;">
                                        <input type="hidden" name="memberId" value="${member.id}">
                                        <input type="hidden" name="teamId" value="${team.id}">
                                        <button type="submit" class="btn btn-sm btn-outline-danger" 
                                                onclick="return confirm('Are you sure you want to delete this member?');">
                                            <i class="fas fa-trash-alt"></i> Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        
        <c:if test="${empty members}">
            <div class="alert alert-info" role="alert">
                <i class="fas fa-info-circle"></i> No team members to display.
            </div>
        </c:if>

        <div class="text-center mt-4">
            <form action="?action=addMember" method="post">
                <input type="text" name="firstName" placeholder="First Name" required>
                <input type="text" name="lastName" placeholder="Last Name" required>
                <input type="email" name="email" placeholder="Email" required>
                <select name="role" required>
                    <option value="" disabled selected>Select Role</option>
                    <option value="PROJECT_MANAGER">Project Manager</option>
                    <option value="DEVELOPER">Developer</option>
                    <option value="DESIGNER">Designer</option>
                </select>
                <!-- send also ${team} -->
                <input type="hidden" name="teamName" value="${team.name}">
                <input type="hidden" name="teamId" value="${team.id}">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-plus"></i> Add New Member
                </button>
            </form>
         
        </div>
    </div>
    
    <script>
        $(document).ready(function() {
            $('#membersTable').DataTable({
                "pageLength": 5,
                "lengthChange": false,
                "info": true,
                "searching": true,
                "language": {
                    "search": "Search by name:",
                    "info": "Showing _START_ to _END_ of _TOTAL_ members",
                    "paginate": {
                        "first": "First",
                        "last": "Last",
                        "next": "Next",
                        "previous": "Previous"
                    }
                },
                "columnDefs": [
                    { "searchable": false, "targets": [1, 2, 3, 4] },
                    { "orderable": false, "targets": 4 }
                ],
                "order": [[0, "asc"]]
            });
        });
    </script>
</body>
</html>
