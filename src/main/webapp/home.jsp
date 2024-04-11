<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style >
*{
	margin:0px;
	padding:0px;
}
table{
width:60%;

}
table,td,th{
	border: 1px solid black;
	border-collapse: collapse;
	
}
th{
background-color: #D8EBF3;
color:black;
}
td{
background-color: white;
color:black;
}
#bg_img{
color:#D8EBF3;
height:100vh;
background-image: url("https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832_960_720.jpg");
background-position: center;
background-repeat: no-repeat;
background-size: cover;
display: flex;
justify-content: center;
flex-direction: column;

}
#a{
	text-decoration: none;
	border: 1px solid white;
	border-radius: 10px;
	width: 100px;
	color: white;
	padding: 5px;
	
}
</style>
</head>
<body>
<div id="bg_img">
<div id="content">
<%User u = (User)request.getSession().getAttribute("user"); %>
<h1>Welcome <%=u.getUsername() %></h1>
<h3>Name :<%=u.getUsername() %></h3>
<h3>Email :<%=u.getUseremail() %></h3>
<h3>Contact :<%=u.getUsercontact() %></h3>
<%String image = new String(Base64.getEncoder().encode(u.getUserimage())); %>
<img alt="" src="data:image/jpeg;base64,<%= image  %>" width="150px" height="150px">
<br><br>
<a href="addtask.jsp" id="a">Add Task</a><br>
</div>
<center><h2>Tasks</h2></center>

<%List<Task> tasks = (List)request.getAttribute("tasks");%>

<center><table>
<tr>
<th>id</th>
<th>title</th>
<th>description</th>
<th>priority</th>
<th>due date</th>
<th>status</th>
<th>delete</th>
<th>Edit</th>
</tr>
<% int num=1; %>
<%for(Task task : tasks){ %>

<tr>
<td><%= task.getTaskid() %></td>
<td><%= task.getTasktitle() %></td>
<td><%= task.getTaskdescription() %></td>
<td><%= task.getTaskpriority()%></td>
<td><%= task.getTaskduedate() %></td>
<td><%= task.getTaskstatus() %></td>
<td><a href="delete?id=<%=task.getTaskid() %>">Delete</a>
<td><a href="edit?id=<%=task.getTaskid() %>">Edit</a>

</td>

</tr>
<%num=num+1; %>
<%} %>

</table>
<a href="logout">LogOut</a>
</center>
</div>

</body>
</html>


