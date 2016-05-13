<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>

        <div class="container">
            <c:choose>
                <c:when test="${empty room}">
                    <p>You are <b>not logged in</b> and not allowed to get cargos state.</p>
                </c:when>
                <c:otherwise>
                    <form:form method="post" commandName="room">
                        <form:hidden path="roomNumber"/>
                        <table>
                            <tr>
                                <td>People count:</td>
                                <td>Price:</td>
                                <td>Description: </td>
                            </tr>
                            <tr>
                                <td><form:input path="peopleCount"/></td>
                                <td><form:input path="price"/></td>
                                <td><form:input path="description"/></td>
                            </tr>
                        </table>
                        <input type="submit" name="my_submit" value="Create">
                    </form:form>
                </c:otherwise>
            </c:choose>
            <%--<form action="${pageContext.request.contextPath}/room/create" method="post" enctype="application/x-www-form-urlencoded">--%>
                <%--<input type="text" size="40" maxlength="256" name="user_name" value="Jenny">--%>
                <%--<input type="text" size="40" maxlength="256" name="second_name" value="Loren">--%>
                <%--<input type="text" size="40" maxlength="256" name="lol" value="LoL">--%>

                <%--<input type="submit" name="my_submit" value="Create">--%>
            <%--</form>--%>
        </div>
        <c:import url="../parts/navigation.jsp"/>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
