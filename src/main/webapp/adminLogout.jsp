<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession existingSession = request.getSession(false);
    if (existingSession != null) {
        existingSession.invalidate();
    }

    response.sendRedirect("admin&staffLogin.jsp");
%>