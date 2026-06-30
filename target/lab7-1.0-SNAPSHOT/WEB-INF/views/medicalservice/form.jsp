<%-- 
    Document   : form
    Created on : Jun 23, 2026, 11:36:07 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="form"
          uri="http://www.springframework.org/tags/form"%>

<%@include file="../layout/header.jsp"%>
<%@include file="../layout/menu.jsp"%>

<c:set var="editing"
       value="${not empty service.serviceCode}" />

<h3>

    <c:choose>

        <c:when test="${editing}">
            Edit Medical Service
        </c:when>

        <c:otherwise>
            Add Medical Service
        </c:otherwise>

    </c:choose>

</h3>

<c:if test="${not empty error}">

    <div class="alert alert-danger">

        ${error}

    </div>

</c:if>

<c:choose>

    <c:when test="${editing}">

        <c:set var="action"
               value="${pageContext.request.contextPath}/medicalservice/edit"/>

    </c:when>

    <c:otherwise>

        <c:set var="action"
               value="${pageContext.request.contextPath}/medicalservice/create"/>

    </c:otherwise>

</c:choose>

<form:form
    method="post"
    modelAttribute="service"
    action="${action}">

    <div class="mb-3">

        <label>Service Code</label>

        <form:input
            path="serviceCode"
            class="form-control"
            readonly="${editing}"/>

        <form:errors
            path="serviceCode"
            cssClass="text-danger"/>

    </div>

    <div class="mb-3">

        <label>Service Name</label>

        <form:input
            path="serviceName"
            class="form-control"/>

        <form:errors
            path="serviceName"
            cssClass="text-danger"/>

    </div>

    <div class="mb-3">

        <label>Category</label>

        <form:input
            path="category"
            class="form-control"/>

        <form:errors
            path="category"
            cssClass="text-danger"/>

    </div>

    <div class="mb-3">

        <label>Description</label>

        <form:textarea
            path="description"
            rows="4"
            class="form-control"/>

    </div>

    <div class="mb-3">

        <label>Fee</label>

        <form:input
            path="fee"
            type="number"
            class="form-control"/>

        <form:errors
            path="fee"
            cssClass="text-danger"/>

    </div>

    <div class="mb-3">

        <label>Duration (Minutes)</label>

        <form:input
            path="duration"
            type="number"
            class="form-control"/>

        <form:errors
            path="duration"
            cssClass="text-danger"/>

    </div>

    <div class="mb-3">

        <label>Status</label>

        <form:select
            path="status"
            class="form-select">

            <form:option value="Available">
                Available
            </form:option>

            <form:option value="Unavailable">
                Unavailable
            </form:option>

        </form:select>

    </div>

    <button
        class="btn btn-primary">

        Save

    </button>

    <a
        class="btn btn-secondary"
        href="${pageContext.request.contextPath}/medicalservice">

        Back

    </a>

</form:form>

<%@include file="../layout/footer.jsp"%>
