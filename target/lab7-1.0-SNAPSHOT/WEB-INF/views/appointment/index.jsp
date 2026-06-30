<%-- 
    Document   : index
    Created on : Jun 23, 2026, 11:46:58 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center mb-3">

        <h3>Appointment Management</h3>

        <a href="${pageContext.request.contextPath}/appointment/create"
           class="btn btn-primary">
            Book Appointment
        </a>

    </div>

    <form action="${pageContext.request.contextPath}/appointment/search"
          method="get"
          class="row g-2 mb-3">

        <div class="col-md-4">

            <input type="text"
                   name="keyword"
                   value="${keyword}"
                   class="form-control"
                   placeholder="Search patient or service">

        </div>

        <div class="col-auto">

            <button class="btn btn-success">

                Search

            </button>

        </div>

    </form>

    <table class="table table-bordered table-hover">

        <thead class="table-primary">

        <tr>

            <th>ID</th>
            <th>Patient</th>
            <th>Medical Service</th>
            <th>Appointment Date</th>
            <th>Status</th>
            <th width="170">Action</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach items="${appointments}" var="a">

            <tr>

                <td>${a.appointmentID}</td>

                <td>${a.patientName}</td>

                <td>${a.serviceName}</td>

                <td>${a.appointmentDate}</td>

                <td>${a.status}</td>

                <td>

                    <a class="btn btn-warning btn-sm"
                       href="${pageContext.request.contextPath}/appointment/edit/${a.appointmentID}">

                        Edit

                    </a>

                    <a class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this appointment?')"
                       href="${pageContext.request.contextPath}/appointment/delete/${a.appointmentID}">

                        Delete

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

<%@include file="../layout/footer.jsp"%>
