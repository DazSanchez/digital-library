<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Búsqueda">
    <jsp:body>
    	<c:if test="${loggedIn}">
	    	<div class="row justify-content-end py-2">
	    		<div class="col text-right">
	    			<a href='<c:url value="/dashboard" />' class="btn btn-outline-primary">
	    				Ir al panel de administrador
	    			</a>
	    		</div>
	    	</div>
    	</c:if>
    	
        <t:main_content>
            <form action="<c:url value="/search_results" />" method="GET">
                <p class="h4">Buscar en la biblioteca</p>
                <div class="row">
                    <div class="form-group col-12 col-sm-6">
                        <label for="type">Tipo de documento</label>
                        <select class="form-control" name="type" id="type">
                        	<c:forEach items="${types}" var="type">
                        		<option value="${type.id}">${type.name}</option>
                        	</c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-12 col-sm-6">
                        <label for="title">Título</label>
                        <input type="text" class="form-control" id="title" name="title" required placeholder="Título del documento">
                    </div>
                    <input type="number" hidden="hidden" value="1" name="page" />
                    <input type="number" hidden="hidden" value="10" name="per_page" />
                    <div class="d-flex col-12 justify-content-end">
                        <button type="submit" class="btn btn-primary">
                            Buscar
                        </button>
                    </div>
                </div>
            </form>
        </t:main_content>
        <c:if test="${!loggedIn}">
        	<p class="text-center text-muted mt-2">
	            <a href="<c:url value="/login" />">Inicia sesión</a> para administrar los documentos.
	        </p>
        </c:if>
    </jsp:body>
</t:layout>
