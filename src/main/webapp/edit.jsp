<%@page import="dto.Task"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
text-align:center;
background-color:#cccccc;

background-image:url("https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg");
background-repeat:no-repeat;
background-size: cover;
display: flex;
justify-content: center;
flex-direction: column;
}
form{
text-align:center;
}
</style>
</head>
<body>
<% Task task=(Task)request.getAttribute("task"); %>
	<form action="updatetask" method="post">
		<label>task id:</label><br>
		<input type= "text" name = "taskid" style="display:none;"><br>
		
		<label>task title:</label><br>
		<input type= "text" name = "tasktitle" value="<%=task.getTasktitle() %>"><br>
		
		<label>task description:</label><br>
		<input type= "text" name = "taskdescription" value="<%=task.getTaskdescription() %>" ><br>
		
		Current Task priority:<h4><%= task.getTaskpriority() %></h4> <input type="text" value="<%=task.getTaskpriority() %>" name="pre-priority" hidden="" %>>
		<label>Options :</label><br>
		
		<label for="option1">
		<input type= "radio" id="option1" value="low" name = "taskpriority" > Low
		</label><br>
		<label for="option2">
		<input type= "radio" id="option2" value="medium" name = "taskpriority" > Medium
		</label><br>
		<label for="option3">
		<input type= "radio" id="option3" value="high" name = "taskpriority" > High
		</label><br>
		
		<label>Due Date:</label><br>
		<input type= "date" name = "taskduedate" value="<%=task.getTaskduedate() %>"><br><br>
		<label>TaskStatus:</label><br>
		<input type= "text" name = "taskstatus" value="<%=task.getTaskstatus() %>"><br><br>
		<input type="submit">
	</form>

</body>
</html>