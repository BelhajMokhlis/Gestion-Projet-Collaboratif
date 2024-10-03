<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/tasks" method="post">
    <input type="hidden" name="action" value="insert">

    <label for="title">Title: </label>
    <input type="text" name="title" required>
    <br>

    <label for="description">Description: </label>
    <input type="text" name="description" required>
    <br>

    <label for="priority">Priority: </label>
    <select name="priority" required>
        <option value="LOW">Low</option>
        <option value="MEDIUM">Medium</option>
        <option value="HIGH">High</option>
    </select>
    <br>

    <label for="dueDate">Due Date (MM/DD/YYYY): </label>
    <input type="text" name="dueDate" placeholder="MM/DD/YYYY" required>
    <br>

    <input type="submit" value="Add new task!">
</form>
</body>
</html>