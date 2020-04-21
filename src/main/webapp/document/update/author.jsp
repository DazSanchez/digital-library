<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Actualización de autor">
	<jsp:body>
        <t:main_content>
        	<p class="h5">Información del autor</p>
        	
        	<form method="POST">
				<div class="form-group">
			    	<label for="name">Nombre(s)<span class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="name"
						required="required" name="authorName"
						value="${document.author.name}" />
				</div>
				<div class="form-group">
			    	<label for="firstSurname">Apellido paterno<span
						class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="firstSurname"
						required="required" name="authorFirstName"
						value="${document.author.firstSurname}" />
				</div>
				<div class="form-group">
			    	<label for="secondSurname">Apellido materno<span
						class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="secondSurname"
						required="required" name="authorSecondName"
						value="${document.author.secondSurname}" />
				</div>
				<div class="d-flex justify-content-end mt-2">
					<a class="btn btn-light mr-2"
						href='<c:url value="/document/update/detail/${document.id}" />'>
						Cancelar
					</a>
	        		<button type="submit" class="btn btn-primary">
						Guardar
					</button>
	        	</div>
        	</form>
        </t:main_content>
    </jsp:body>
</t:layout>
