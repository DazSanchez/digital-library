<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Actualización de género">
	<jsp:body>
        <t:main_content>
        	<p class="h5">Información del género</p>
        	
        	<form method="POST">
				<div class="form-group">
			    	<label for="genre">Nombre del género<span
						class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="genre"
						required="required" placeholder="Historia" name="genre" value="${document.genre.name}"/>
				</div>
				<div class="d-flex justify-content-end mt-2">
					<a class="btn btn-light mr-2" href='<c:url value="/document/update/detail/${document.id}" />'>
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
