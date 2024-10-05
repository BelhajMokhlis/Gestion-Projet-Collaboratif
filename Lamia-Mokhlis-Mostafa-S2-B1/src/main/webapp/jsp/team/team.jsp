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
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4 text-center">${team.name}</h1>
        
        <c:if test="${not empty members}">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td>${member.firstName}</td>
                                <td>${member.lastName}</td>
                                <td>${member.email}</td>
                                <td>${member.role }</td>
                                <td>
                                    <a href="editMember?id=${member.id}" class="btn btn-sm btn-outline-primary mr-2">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <a href="deleteMember?id=${member.id}" class="btn btn-sm btn-outline-danger" 
                                       onclick="return confirm('Are you sure you want to delete this member?');">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
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
            <a href="addMember" class="btn btn-success">
                <i class="fas fa-plus"></i> Add New Member
            </a>
        </div>
    </div>
</body>
</html>
