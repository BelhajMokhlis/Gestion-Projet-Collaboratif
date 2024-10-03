<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>Error</title>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="col-md-8 text-center">
            <div class="center">
                <h1>Oops!</h1>
                <h2>You made an input mistake</h2>
                <div class="alert alert-danger">
                    ${errorMessage}
                </div>
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/jsp/tasks.jsp" class="btn btn-primary btn-lg">
                        Go back
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>