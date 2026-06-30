<%-- 
    Document   : form
    Created on : Jun 23, 2026, 11:42:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<c:set var="editing" value="${patient.patientID > 0}" />

<div class="container mt-4">

    <h3>

        <c:choose>

            <c:when test="${editing}">
                Edit Patient
            </c:when>

            <c:otherwise>
                Add Patient
            </c:otherwise>

        </c:choose>

    </h3>

    <hr>

    <c:if test="${not empty error}">

        <div class="alert alert-danger">

            ${error}

        </div>

    </c:if>

    <form:form
        method="post"
        modelAttribute="patient"
        action="${pageContext.request.contextPath}/patient/save">

        <form:hidden path="patientID"/>

        <div class="mb-3">

            <label class="form-label">

                Full Name

            </label>

            <form:input
                path="fullName"
                cssClass="form-control"/>

            <form:errors
                path="fullName"
                cssClass="text-danger"/>

        </div>

        <div class="mb-3">

            <label class="form-label">

                Date Of Birth

            </label>

            <form:input
                path="dateOfBirth"
                type="date"
                cssClass="form-control"/>

            <form:errors
                path="dateOfBirth"
                cssClass="text-danger"/>

        </div>

        <div class="mb-3">

            <label class="form-label">

                Email

            </label>

            <form:input
                path="email"
                type="email"
                cssClass="form-control"/>

            <form:errors
                path="email"
                cssClass="text-danger"/>

        </div>

        <div class="mb-3">

            <label class="form-label">

                Phone Number

            </label>

            <form:input
                path="phoneNumber"
                cssClass="form-control"/>

            <form:errors
                path="phoneNumber"
                cssClass="text-danger"/>

        </div>

        <button
            type="submit"
            class="btn btn-primary">

            Save

        </button>

        <a href="${pageContext.request.contextPath}/patient"
           class="btn btn-secondary">

            Back

        </a>

    </form:form>

</div>

<%@include file="../layout/footer.jsp"%>
