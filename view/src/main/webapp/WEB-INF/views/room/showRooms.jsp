<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>
        <c:import url="../parts/navigation.jsp"/>
        <div class="container">
            <c:choose>
                <c:when test="${empty rooms}">
                    <p>You are <b>not logged in</b> and not allowed to get cargos state.</p>
                </c:when>
                <c:otherwise>
                    <h2>Room list</h2>
                    <table id="roomListTable" class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-5">Number</th>
                            <th class="col-md-7">People count</th>
                            <th class="col-md-7">Price</th>
                            <th class="col-md-7">Description</th>
                            <th class="col-md-7">Action</th>
                        </tr>
                        <c:forEach items="${rooms}" var="room">
                            <tr>
                                <td>${room.getRoomNumber()}</td>
                                <td>${room.getPeopleCount()}</td>
                                <td>${room.getPrice()}</td>
                                <td>${room.getDescription()}</td>
                                <td><a href="${pageContext.request.contextPath}/room/edit/${room.getRoomNumber()}">Edit</a>
                                    <a href="${pageContext.request.contextPath}/room/delete/${room.getRoomNumber()}">Delete</a>
                                    <a href="${pageContext.request.contextPath}/order/create/${room.getRoomNumber()}">Make order</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <c:import url="../parts/paging.jsp"/>
            <br/>
            <a href="${pageContext.request.contextPath}/room/create">Create new room</a>
        </div>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
