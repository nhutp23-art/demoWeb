<%-- 
    Document   : index
    Created on : Jun 23, 2026, 11:44:39 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center mb-3">

        <h3>Doctor Assignment</h3>

        <a href="${pageContext.request.contextPath}/assignment/create"
           class="btn btn-primary">
            Assign Doctor
        </a>

    </div>

    <table class="table table-bordered table-hover">

        <thead class="table-primary">

        <tr>

            <th>ID</th>
            <th>Service Code</th>
            <th>Service Name</th>
            <th>Doctor ID</th>
            <th>Doctor Name</th>
            <th>Assignment Date</th>
            <th width="170">Action</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach items="${assignments}" var="a">

            <tr>

                <td>${a.assignmentID}</td>

                <td>${a.serviceCode}</td>

                <td>${a.serviceName}</td>

                <td>${a.doctorID}</td>

                <td>${a.doctorName}</td>

                <td>${a.assignmentDate}</td>

                <td>

                    <a class="btn btn-warning btn-sm"
                       href="${pageContext.request.contextPath}/assignment/edit/${a.assignmentID}">
                        Edit
                    </a>

                    <a class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this assignment?')"
                       href="${pageContext.request.contextPath}/assignment/delete/${a.assignmentID}">
                        Delete
                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

<%@include file="../layout/footer.jsp"%>
