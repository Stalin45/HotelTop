<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:import url="../parts/header.jsp"/>
<c:import url="../parts/navigation.jsp"/>
<div class="container">
    <c:choose>
        <c:when test="${empty room}">
            <p>You are <b>not logged in</b> and not allowed to get cargos state.</p>
        </c:when>
        <c:otherwise>
            <form:form action="${pageContext.request.contextPath}/room/edit" method="post" commandName="room">
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
                <input type="submit" value="Complete edition">
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="../parts/footer.jsp"/>
</body>
</html>
