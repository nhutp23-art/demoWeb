<%-- 
    Document   : form
    Created on : Jun 23, 2026, 11:38:52 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<c:set var="editing" value="${doctor.doctorID > 0}" />

<div class="container mt-4">

    <h3>

        <c:choose>

            <c:when test="${editing}">
                Edit Doctor
            </c:when>

            <c:otherwise>
                Add Doctor
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
        modelAttribute="doctor"
        action="${pageContext.request.contextPath}/doctor/save">

        <form:hidden path="doctorID"/>

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
                Specialty
            </label>

            <form:input
                path="specialty"
                cssClass="form-control"/>

            <form:errors
                path="specialty"
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

        <a href="${pageContext.request.contextPath}/doctor"
           class="btn btn-secondary">

            Back

        </a>

    </form:form>

</div>

<%@include file="../layout/footer.jsp"%>