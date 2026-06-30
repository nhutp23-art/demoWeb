<%-- 
    Document   : index
    Created on : Jun 23, 2026, 11:35:36 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-3">

    <h3>Medical Service Management</h3>

    <a href="${pageContext.request.contextPath}/medicalservice/create"
       class="btn btn-primary">
        Add Service
    </a>

</div>

<form action="${pageContext.request.contextPath}/medicalservice/search"
      method="get"
      class="row g-2 mb-3">

    <div class="col-md-4">

        <input
            type="text"
            name="keyword"
            value="${keyword}"
            class="form-control"
            placeholder="Search code or name">

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

            <th>Code</th>

            <th>Name</th>

            <th>Category</th>

            <th>Fee</th>

            <th>Duration</th>

            <th>Status</th>

            <th width="170">Action</th>

        </tr>

    </thead>

    <tbody>

        <c:forEach items="${services}" var="s">

            <tr>

                <td>${s.serviceCode}</td>

                <td>${s.serviceName}</td>

                <td>${s.category}</td>

                <td>${s.fee}</td>

                <td>${s.duration} mins</td>

                <td>${s.status}</td>

                <td>

                    <a class="btn btn-warning btn-sm"
                       href="${pageContext.request.contextPath}/medicalservice/edit/${s.serviceCode}">
                        Edit
                    </a>

                    <a class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this service?')"
                       href="${pageContext.request.contextPath}/medicalservice/delete/${s.serviceCode}">
                        Delete
                    </a>

                </td>

            </tr>

        </c:forEach>

    </tbody>

</table>

<%@include file="../layout/footer.jsp"%>
