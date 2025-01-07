<%@page import="com.mycompany.mavenproject1.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
       <%
           out.println(FactoryProvider.getfactory()+"<br>");
           out.println(FactoryProvider.getfactory()+"<br>");
           out.println(FactoryProvider.getfactory());
       
       
       
       
       
       %>
        
        
        
        
        
    </body>
</html>