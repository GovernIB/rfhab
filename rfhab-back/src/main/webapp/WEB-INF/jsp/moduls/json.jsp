<%@ page contentType="application/json; charset=UTF-8" %>
<%
    String jsonData = (String) request.getAttribute("jsonData");
    if (jsonData != null) {
        out.print(jsonData);
    } else {
        out.print("{}");
    }
%>