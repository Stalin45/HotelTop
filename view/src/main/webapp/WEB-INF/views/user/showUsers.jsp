<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>
        <c:import url="../parts/navigation.jsp"/>
        <div class="container">
            <c:choose>
                <c:when test="${empty users}">
                    <p>No one <b>user</b> found</p>
                </c:when>
                <c:otherwise>
                    <h2>User list</h2>
                    <table id="userListTable" class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-5">User ID</th>
                            <th class="col-md-7">Name</th>
                            <th class="col-md-7">Surname</th>
                            <th class="col-md-7">Rating</th>
                            <th class="col-md-7">Action</th>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.getUserId()}</td>
                                <td>${user.getName()}</td>
                                <td>${user.getSurname()}</td>
                                <td>${user.getBonusPoints()}</td>
                                <td><a href="${pageContext.request.contextPath}/user/edit/${user.getUserId()}">Edit</a>
                                    <a href="${pageContext.request.contextPath}/user/delete/${user.getUserId()}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <c:import url="../parts/paging.jsp"/>
            <br/>
            <a href="${pageContext.request.contextPath}/user/create">Register new user</a>
        </div>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
