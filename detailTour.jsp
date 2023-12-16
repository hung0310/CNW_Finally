<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Model.BEAN.Tour" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="Model.BEAN.User"%>
<%@page import="Model.BEAN.Comment"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .select-tours {
            text-align: center;
            margin-bottom: 20px;
        }

        .custom-table {
            border: 1px solid #ccc;
            padding: 20px;
        }

        .tour-card {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-top: 20px;
        }

        .tour-details {
            text-align: center;
        }

        .detail-description {
            margin: 20px 0;
            padding: 10px;
            background-color: #f2f2f2;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            line-height: 1.5;
            text-align: left; 
        }

        .price {
            margin-top: 10px;
        }

        .submit-tour {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 20px;
            cursor: pointer;
        }

        .no-tours {
            text-align: center;
            margin-top: 20px;
        }

        iframe {
            width: 853px;
            height: 480px;
        }
    </style>
    <link rel="stylesheet" href="./View/CSS/CommentStyle.css">
</head>
<body>
    <div class="container">
        <div class="select-tours">
            <div class="custom-table">
                <% 
                    Tour tour = (Tour) request.getAttribute("tour_detail");
                    if (tour != null) {
                %>
                <h2>Thông Tin Chi Tiết Của Tour <%=tour.getLocation() %></h2>
                <div class="tour-card">
                    <div class="tour-details">
                        <div class="detail-description"><%= tour.getDescription() %></div>
                        <iframe src="<%= tour.getVideo_link() %>"></iframe>
                        <div class="price" style="color: red; font-size: 20px;">Giá vé: <%= tour.getPrice() %>/Người</div>
                        <a href=""><button class="submit-tour">Đặt tour</button></a>
                    </div>
                </div>
                <% 
                    } else {
                %>
                <div class="no-tours">
                    <h1>No tours available</h1>
                </div>
                <% 
                    }
                %>
            </div>
        </div>
    </div>
	<%
		String username = (String)session.getAttribute("username");
		session.setAttribute("username", username);
	%>
	<form action="detailTourServlet?tourId=<%= request.getParameter("tourId") %>" method="post">
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
    	<%
        	if (name != null) {
    	%>
    		function sendComment() {
    			var content = document.getElementById("comment-text").value;
    			addComment('<%= name.get_fullname() %>', content);
    		}
    	<%
            }
        %>
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