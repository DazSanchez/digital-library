<%-- 
    Document : index
    Created on : 24/03/2020, 01:12:02 AM 
    Author : hsanchez
--%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Búsqueda">
    <jsp:body>
        <t:main_content>
            <form action="<c:url value="/search_results" />" method="GET">
                <p class="h4">Buscar en la biblioteca</p>
                <div class="row">
                    <div class="form-group col-12 col-sm-6">
                        <label for="type">Tipo de documento</label>
                        <select class="form-control" name="type" id="type">
                            <option value="1">Libro</option>
                            <option value="2">Periódico</option>
                            <option value="3">Revista</option>
                        </select>
                    </div>
                    <div class="form-group col-12 col-sm-6">
                        <label for="title">Título</label>
                        <input type="text" class="form-control" id="title" name="title" required placeholder="Título del documento">
                    </div>
                    <div class="d-flex col-12 justify-content-end">
                        <button class="btn btn-primary">
                            Buscar
                        </button>
                    </div>
                </div>
            </form>
        </t:main_content>
        <p class="text-center text-muted mt-2">
            <a href="<c:url value="/login" />">Inicia sesión</a> para administrar los documentos.
        </p>
    </jsp:body>
</t:layout>
