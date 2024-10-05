<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Edit Task</h2>

        <form action="${pageContext.request.contextPath}/tasks" method="post">
            <input type="hidden" name="taskId" value="${task.id}">
            <input type="hidden" name="action" value="update">
            
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" id="title" name="title" class="form-control" value="${task.title}" required>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea id="description" name="description" class="form-control" rows="4" required>${task.description}</textarea>
            </div>
            
            <div class="mb-3">
                <label for="priority" class="form-label">Priority</label>
                <select id="priority" name="priority" class="form-select">
                    <option value="LOW" ${task.priority == 'LOW' ? 'selected' : ''}>Low</option>
                    <option value="MEDIUM" ${task.priority == 'MEDIUM' ? 'selected' : ''}>Medium</option>
                    <option value="HIGH" ${task.priority == 'HIGH' ? 'selected' : ''}>High</option>
                </select>
            </div>            
            
            <div class="mb-3">
                <label for="dueDate" class="form-label">Due Date (MM/DD/YYYY)</label>
                <input type="text" id="dueDate" name="dueDate" class="form-control" value="${task.dueDate.format(java.time.format.DateTimeFormatter.ofPattern('MM/dd/yyyy'))}" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Update Task</button>
            <a href="${pageContext.request.contextPath}/tasks?action=list&projectID=6" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script></body>
</html>
