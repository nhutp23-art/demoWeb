<%-- 
    Document   : form
    Created on : Jun 23, 2026, 11:47:18 AM
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
       value="${appointment.appointmentID>0}" />

<div class="container mt-4">

    <h3>

        <c:choose>

            <c:when test="${editing}">
                Update Appointment
            </c:when>

            <c:otherwise>
                Book Appointment
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
        modelAttribute="appointment"
        action="${pageContext.request.contextPath}/appointment/save">

        <form:hidden path="appointmentID"/>

        <div class="mb-3">

            <label>

                Patient

            </label>

            <form:select
                path="patientID"
                cssClass="form-select">

                <form:option value="0">

                    -- Select Patient --

                </form:option>

                <c:forEach
                    items="${patients}"
                    var="p">

                    <form:option
                        value="${p.patientID}">

                        ${p.fullName}

                    </form:option>

                </c:forEach>

            </form:select>

        </div>

        <div class="mb-3">

            <label>

                Medical Service

            </label>

            <form:select
                path="serviceCode"
                cssClass="form-select">

                <form:option value="">

                    -- Select Service --

                </form:option>

                <c:forEach
                    items="${services}"
                    var="s">

                    <form:option
                        value="${s.serviceCode}">

                        ${s.serviceName}

                    </form:option>

                </c:forEach>

            </form:select>

        </div>

        <div class="mb-3">

            <label>

                Appointment Date

            </label>

            <form:input
                path="appointmentDate"
                type="date"
                cssClass="form-control"/>

        </div>

        <div class="mb-3">

            <label>

                Status

            </label>

            <form:select
                path="status"
                cssClass="form-select">

                <form:option value="Pending">Pending</form:option>

                <form:option value="Confirmed">Confirmed</form:option>

                <form:option value="Completed">Completed</form:option>

                <form:option value="Cancelled">Cancelled</form:option>

            </form:select>

        </div>

        <button
            class="btn btn-primary">

            Save

        </button>

        <a
            href="${pageContext.request.contextPath}/appointment"
            class="btn btn-secondary">

            Back

        </a>

    </form:form>

</div>

<%@include file="../layout/footer.jsp"%>
