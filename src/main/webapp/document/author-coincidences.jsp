<%-- 
    Document   : detail
    Created on : 25/03/2020, 02:15:29 AM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Coincidencias de autor">
	<jsp:body>
        <t:main_content>
        	<p class="h6">Encontramos algunas coincidencias para el autor que ingresaste</p>
        	<p>Puedes seleccionar uno existente o dejar el actual</p>
        	
        	<form action='<c:url value="/document/create/author-coincidences" />' method="post" class="pb-2">
        		<ul class="list-group">
        			<li class="list-group-item d-flex align-items-center">
        				<input class="mr-2" type="radio" id="default" name="selectedAuthor" checked="checked" value="${null}" />
        				<label class="m-0" for="default">${current.fullName} <span class="text-muted">(actual)</span></label>
        			</li>
        			<c:forEach items="${authorCoincidences}" var="author">
        				<li class="list-group-item d-flex align-items-center">
	        				<input class="mr-2" type="radio" id="default" name="selectedAuthor" checked="checked" value="${genre.id}" />
	        				<label class="m-0" for="default">${author.fullName}</label>
	        			</li>
        			</c:forEach>
        		</ul>
        	</form>
        	
        	<div class="d-flex justify-content-end">
        		<button class="btn btn-primary" type="submit">Continuar</button>
        	</div>
        </t:main_content>
    </jsp:body>
</t:layout>