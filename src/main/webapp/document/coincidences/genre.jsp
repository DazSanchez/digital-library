<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Coincidencias de género">
	<jsp:body>
        <t:main_content>
        	<p class="h6">Encontramos algunas coincidencias para el género que ingresaste</p>
        	<p>Puedes seleccionar uno existente o dejar el actual</p>
        	
        	<form
				action='<c:url value="/document/create/genre-coincidences" />'
				method="post" class="pb-2">
        		<ul class="list-group">
        			<li class="list-group-item d-flex align-items-center">
        				<input class="mr-2" type="radio" id="actual"
						name="selectedGenre" checked="checked" value="${null}" />
        				<label class="m-0" for="actual">${current} <span
							class="text-muted">(actual)</span></label>
        			</li>
        			<c:forEach items="${genreCoincidences}" var="genre">
        				<li class="list-group-item d-flex align-items-center">
	        				<input class="mr-2" type="radio" id="genre-${genre.id}"
							name="selectedGenre" value="${genre.id}" />
	        				<label class="m-0" for="genre-${genre.id}">${genre}</label>
	        			</li>
        			</c:forEach>
        		</ul>
        		<div class="d-flex justify-content-end mt-2">
	        		<button class="btn btn-primary" type="submit">Continuar</button>
	        	</div>
        	</form>
        </t:main_content>
    </jsp:body>
</t:layout>