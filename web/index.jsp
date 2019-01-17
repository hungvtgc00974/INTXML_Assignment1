<%--
    Document   : index
    Created on : Jan 9, 2019, 9:27:05 AM
    Author     : Arthas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
    </head>
    <body>
        ${info}
        <form action="ActionProcess" method="POST">
            <input type="hidden" name="act" value="getdata"/>
            <input type="submit" value="Export Customer List To XML"/>
        </form>

        <form action="ActionProcess" method="POST">
            <input type="hidden" name="act" value="validatedata"/>
            <input type="submit" value="Validate XML"/>
        </form>

        ${validateMessage}

        <form action="ActionProcess" method="POST">
            <input type="hidden" name="act" value="showdata"/>
            <input type="submit" value="Show Customers In London"/>
        </form>

    </body>
</html>
