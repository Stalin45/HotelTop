<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <c:import url="../parts/header.jsp"/>
        <c:import url="../parts/navigation.jsp"/>
        <div class="container">
            <c:choose>
                <c:when test="${empty orders}">
                    <p>No one <b>order</b> found</p>
                </c:when>
                <c:otherwise>
                    <h2>Order list</h2>
                    <table id="orderListTable" class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-5">ID</th>
                            <th class="col-md-7">Client</th>
                            <th class="col-md-7">Room number</th>
                            <th class="col-md-7">Total price</th>
                            <th class="col-md-7">Order date</th>
                            <th class="col-md-7">Days</th>
                            <th class="col-md-7">Status</th>
                            <th class="col-md-7">Actions</th>
                        </tr>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.getOrderId()}</td>
                                <td>${order.getUser().getName()} ${order.getUser().getSurname()}</td>
                                <td>${order.getRoom().getRoomNumber()}</td>
                                <td>${order.getTotalPrice()}</td>
                                <td>${order.getOrderDate()}</td>
                                <td>${order.getDays()}</td>
                                <td>${order.getStatus()}</td>
                                <c:if test="${order.getStatus() eq 'WAITING'}">
                                    <td><a href="${pageContext.request.contextPath}/order/confirm/${order.getOrderId()}">Confirm</a>
                                        <a href="${pageContext.request.contextPath}/order/cancel/${order.getOrderId()}">Cancel</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <c:import url="../parts/paging.jsp"/>
        </div>
        <c:import url="../parts/footer.jsp"/>
    </body>
</html>
