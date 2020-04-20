<%-- 
    Document   : detail
    Created on : 25/03/2020, 02:15:29 AM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Resultado de eliminación">
	<jsp:body>
        <t:main_content>
        	<c:choose>
        		<c:when test="${error == null}">
        			<div class="alert alert-success" role="alert">
					    <h4 class="alert-heading">El documento se eliminó exitosamente!</h4>
					</div>
					<div class="d-flex justify-content-end align-items-center">
						<a href='<c:url value="/dashboard" />'>Volver al menu principal</a>
		        	</div>
        		</c:when>
        		<c:otherwise>
        			<div class="alert alert-danger" role="alert">
					    <h4 class="alert-heading">Ocurrió un error al eliminar el documento!</h4>
					    <p>${error}</p>
					</div>
					<div class="d-flex justify-content-end">
						<a href='<c:url value="/dashboard" />'>Volver al menu principal</a>
		        	</div>
        		</c:otherwise>
        	</c:choose>
        </t:main_content>
    </jsp:body>
</t:layout>