<%-- 
    Document   : index
    Created on : Jun 23, 2026, 11:42:18 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center mb-3">

        <h3>Patient Management</h3>

        <a href="${pageContext.request.contextPath}/patient/create"
           class="btn btn-primary">
            Add Patient
        </a>

    </div>

    <form action="${pageContext.request.contextPath}/patient/search"
          method="get"
          class="row g-2 mb-3">

        <div class="col-md-4">

            <input
                type="text"
                name="keyword"
                value="${keyword}"
                class="form-control"
                placeholder="Search patient...">

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
                <th>Full Name</th>
                <th>Date Of Birth</th>
                <th>Email</th>
                <th>Phone</th>
                <th width="170">Action</th>

            </tr>

        </thead>

        <tbody>

            <c:forEach items="${patients}" var="p">

                <tr>

                    <td>${p.patientID}</td>

                    <td>${p.fullName}</td>

                    <td>${p.dateOfBirth}</td>

                    <td>${p.email}</td>

                    <td>${p.phoneNumber}</td>

                    <td>

                        <a class="btn btn-warning btn-sm"
                           href="${pageContext.request.contextPath}/patient/edit/${p.patientID}">

                            Edit

                        </a>

                        <a class="btn btn-danger btn-sm"
                           onclick="return confirm('Delete this patient?')"
                           href="${pageContext.request.contextPath}/patient/delete/${p.patientID}">

                            Delete

                        </a>

                    </td>

                </tr>

            </c:forEach>

        </tbody>

    </table>

</div>

<%@include file="../layout/footer.jsp"%>
