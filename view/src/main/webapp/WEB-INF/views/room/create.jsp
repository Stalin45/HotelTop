<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>

        <a href="${pageContext.request.contextPath}/lol">click me</a>

        <div class="container">
            <c:choose>
                <c:when test="${empty rooms}">
                    <p>You are <b>not logged in</b> and not allowed to get cargos state.</p>
                </c:when>
                <c:otherwise>
                    <h2>Cargos State</h2>
                    <table id="roomListTable" class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-5">Number</th>
                            <th class="col-md-7">People count</th>
                            <th class="col-md-7">Price</th>
                            <th class="col-md-7">Description</th>
                        </tr>
                        <c:forEach items="${rooms}" var="room">
                            <tr>
                                <td>${room.getRoomNumber()}</td>
                                <td>${room.getPeopleCount()}</td>
                                <td>${room.getPrice()}</td>
                                <td>${room.getDescription()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>

        <c:import url="../parts/navigation.jsp"/>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
