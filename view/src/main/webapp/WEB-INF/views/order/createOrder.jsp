<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>

        <div class="container">
            <c:choose>
                <c:when test="${empty makeOrderDTO}">
                    <p>No one <b>order</b> found</p>
                </c:when>
                <c:otherwise>
                    <form:form method="post" commandName="makeOrderDTO">
                        <form:hidden path="roomNumber"/>
                        <table>
                            <tr>
                                <td>Choose user:</td>
                                <td>Date from:</td>
                                <td>Date to:</td>
                            </tr>
                            <tr>
                                <td>
                                    <select name="userId">
                                        <c:forEach var="user" items="${users}">
                                            <option value="${user.getUserId()}">${user.getName()} ${user.getSurname()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><form:input path="dateFrom"/></td>
                                <td><form:input path="dateTo"/></td>
                            </tr>
                        </table>
                        <input type="submit" name="my_submit" value="Create">
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
        <c:import url="../parts/navigation.jsp"/>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
