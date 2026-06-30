<%-- 
    Document   : history
    Created on : Jun 23, 2026, 11:47:39 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<div class="container mt-4">

    <h3>

        Appointment History

    </h3>

    <table class="table table-bordered">

        <thead class="table-primary">

            <tr>

                <th>Service</th>

                <th>Date</th>

                <th>Status</th>

            </tr>

        </thead>

        <tbody>

        <c:forEach items="${appointments}" var="a">

            <tr>

                <td>${a.serviceName}</td>

                <td>${a.appointmentDate}</td>

                <td>${a.status}</td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

    <a href="${pageContext.request.contextPath}/appointment"
       class="btn btn-secondary">

        Back

    </a>

</div>

<%@include file="../layout/footer.jsp"%>
