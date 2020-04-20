<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Confirmar eliminación de documento">
	<jsp:body>
        <t:main_content>
        	<c:choose>
        		<c:when test="${error == null}">
        			<form method="POST">
        				<p class="h4">¿Está seguro de que quiere eliminar el documento "${title}"?</p>
		       			<p class="text-danger font-weight-bold">Esta acción es irreversible</p>
		       			
		       			<div class="form-check">
						  <input class="form-check-input" 
						  	type="checkbox" 
						  	name="confirm" 
						  	value="true" 
						  	id="delete" 
						  	required="required" />
						  <label class="form-check-label" for="delete">
						    Sí, estoy seguro
						  </label>
						</div>
		       			
						<div class="d-flex justify-content-end align-items-center">
							<a href="#" id="back" class="mr-2 btn btn-light">Cancelar</a>
			        		<button type="submit" class="btn btn-danger ml-2">
								Confirmar
							</button>
			        	</div>
        			</form>
        		</c:when>
        		<c:otherwise>
        			<div class="alert alert-danger" role="alert">
					    ${error}
					</div>
					<div class="d-flex justify-content-end">
						<a href='<c:url value="/dashboard" />'>Volver al menú</a>
		        	</div>
        		</c:otherwise>
        	</c:choose>
        </t:main_content>
        
        <script>
			const back = document.getElementById("back");
			const referrer = document.referrer;
			
			const isFromSearch = referrer.includes("/document/detail") || referrer.includes("/document/delete/search/results");
			
			back.setAttribute("href", isFromSearch ? referrer : "/digital_library/document/delete/search");
		</script>
    </jsp:body>
</t:layout>