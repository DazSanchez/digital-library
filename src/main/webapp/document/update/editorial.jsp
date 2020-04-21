<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Actualización de editorial">
	<jsp:body>
        <t:main_content>
        	<p class="h5">Información de la editorial</p>
        	
        	<form method="POST">
				<div class="form-group">
			    	<label for="editorial">Nombre de la editorial<span
						class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="editorial"
						required="required" placeholder="Grupo Planeta" name="editorial"
						value="${document.editorial.name}" />
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
