<%-- 
    Document   : detail
    Created on : 25/03/2020, 02:15:29 AM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Eliminar un documento">
	<jsp:body>
        <t:main_content>
        	<p class="h4">Ingrese el nombre y tipo de documento para buscar</p>
        	
        	<form action="<c:url value="/document/delete/search/results" />" method="GET">
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
                            Continuar
                        </button>
                    </div>
                </div>
            </form>
        </t:main_content>
    </jsp:body>
</t:layout>