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
    	<div class="row d-flex justify-content-between align-items-center w-100">
    		<h1 class="col">Project Tasks</h1>
    		<div class="col text-right">
        		<a href="${pageContext.request.contextPath}/tasks?action=create&projectID=${projectID}" class="btn btn-success">Add New Task</a>
    		</div>
		</div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Task title</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Due Date</th>
                    <th>Assigned To</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
    			<c:forEach var="task" items="${tasks}">
        			<tr>
            			<td>${task.title}</td>
            			<td>${task.description}</td>
            			<td>${task.status}</td>
            			<td>${task.dueDate}</td>
            			<td>
    						<form action="${pageContext.request.contextPath}/tasks" method="post">
                            <input type="hidden" name="action" value="assignMember">
                            <input type="hidden" name="taskID" value="${task.taskID}">
                            <select name="memberID" class="form-control">
                                <option value="">-- Select Member --</option>
                                <c:forEach var="member" items="${members}">
                                    <option value="${member.id}" <c:if test="${task.member != null && task.member.id == member.id}">selected</c:if>>${member.lastName} ${member.firstName}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-primary mt-1">Assign</button>
                        </form>
						</td>
            			<td>
                			<form action="${pageContext.request.contextPath}/tasks" method="get" style="display:inline;">
                    			<input type="hidden" name="action" value="edit">
                    			<input type="hidden" name="taskID" value="${task.taskID}">
                    			<button type="submit" class="btn btn-primary">Edit</button>
                			</form>
                			<form action="${pageContext.request.contextPath}/tasks" method="post" style="display:inline;">
                    			<input type="hidden" name="action" value="delete">
                    			<input type="hidden" name="taskID" value="${task.taskID}">
                    			<input type="hidden" name="projectID" value="${param.projectID}">
                    			<button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this task?');">Delete</button>
                			</form>
            			</td>
        			</tr>
    			</c:forEach>
			</tbody>
        </table>
    </div>
    <nav aria-label="Page navigation">
    <ul class="pagination mx-auto">
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${currentPage - 1}&size=5">Previous</a>
            </li>
        </c:if>
        
        <c:forEach begin="1" end="${totalPages}" var="i">
            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${i}&size=5">${i}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link" href="?action=list&projectID=${param.projectID}&page=${currentPage + 1}&size=5">Next</a>
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