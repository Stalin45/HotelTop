<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <body>
        <c:set var="prevPage" value="${page-1}"/>
        <c:set var="nextPage" value="${page+1}"/>
        <br/>
        <div class="paging">
            <b>Pages: </b>
            <c:if test="${page ne 1}">
                <a href="${pageContext.request.contextPath}/room/show/${prevPage}">${prevPage}</a>
            </c:if>
            <b>${page}</b>
            <c:if test="${page ne pageCount}">
                <a href="${pageContext.request.contextPath}/room/show/${nextPage}">${nextPage}</a>
            </c:if>
        </div>
        <br/>
    </body>
</html>
