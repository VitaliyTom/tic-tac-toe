<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page session="false" %>


<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Крестики-нолики</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>

    <%--   <style>form {margin: 200px;}</style>  --%>


</head>
<body>


<center>
    <h2> Игра Крестики-нолики</h2>


    <form:form method="post" action="/controller/run" modelAttribute="fKey">


        <table>
            <tr>
                <td colspan="2" align="left">


                    <form:input type="text" path="k1" name="k1"/>
                </td>
                <td colspan="2" align="center">
                    <form:input type="text" path="k2" name="k2"/>
                </td>
                <td colspan="2" align="right">
                    <form:input type="text" path="k3" name="k3"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="left">
                    <form:input type="text" path="k4" name="k4"/>
                </td>
                <td colspan="2" align="left">
                    <form:input type="text" path="k5" name="k5"/>
                </td>
                <td colspan="2" align="left">
                    <form:input type="text" path="k6" name="k6"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="left">
                    <form:input type="text" path="k7" name="k7"/>
                </td>
                <td colspan="2" align="left">
                    <form:input type="text" path="k8" name="k8"/>
                </td>
                <td colspan="2" align="left">
                    <form:input type="text" path="k9" name="k9"/>

                </td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="Submit"/></td>
            </tr>

        </table>

        <%--
                <div id="blink7"><a href="/pages/login/register.html">[ Регистрация ]</a></div>
        --%>
    </form:form>

    <a href="../../index.jsp">сначало</a>
</center>


</body>
</html>
