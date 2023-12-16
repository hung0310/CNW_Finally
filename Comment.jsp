<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.BEAN.Comment"%>
<%@page import="Model.BEAN.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./View/CSS/CommentStyle.css">
    <title>Comment Interface</title>
</head>
<body>
	<%
		String username = (String)session.getAttribute("username");
		session.setAttribute("username", username);
		String idTour = (String)request.getAttribute("tourId");
		session.setAttribute("tourId", idTour);
	%>
	<form action="comment?tourId=<%= idTour %>" method="post">
	    <div id="comment-container">
        <div id="comment-list"></div>
        <div id="comment-input">
            <img id="user-image" src="./images/user-avatar.png" alt="User Image">
            <textarea id="comment-text" placeholder ="Type your comment" name="content"></textarea>
            <button type="submit" onclick="sendComment()">Send</button>
        </div>
    </div>
	</form>
    <script src="./View/JS/CommentScript.js"></script>
    <script>
    	<% 
    		User name = (User) request.getAttribute("full_name");
    	%>
    		function sendComment() {
    			var content = document.getElementById("comment-text").value;
    			addComment('<%= name.get_fullname() %>', content);
    			document.getElementById("comment-text").value = "";
    		}
		<%
            ArrayList<Comment> list = (ArrayList<Comment>) request.getAttribute("commentArray");
            if (list == null || list.isEmpty()) {
        %>
            <h1>No tours available</h1>
        <%
            } else {
                for (Comment comment : list) {
                	
        %>
                    addComment('<%= comment.get_nameuser() %>', '<%= comment.getComment() %>');
        <%
                }
            }
        %> 
    </script>
</body>
</html>
