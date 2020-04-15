<%-- 
    Document   : results
    Created on : 25/03/2020, 12:51:45 AM
    Author     : hsanchez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:layout title="Resultado de búsqueda">
	<jsp:attribute name="back">
        <a class="btn-sm" href="<c:url value="/" />">
            &#11104; Volver a buscar
        </a>
    </jsp:attribute>
	<jsp:body>
        <t:main_content>
            <p class="h6">
                Resultados para: "${title}"
            </p>
            
            <c:choose>
            	<c:when test="${error != null}">
            		<div class="alert alert-danger" role="alert">
					  ${error}
					</div>
            	</c:when>
            	<c:when test="${isEmpty}">
            		<div class="alert alert-info" role="alert">
					  No se han encontrado resultados para la búsqueda <strong>"${title}"</strong>
					</div>
            	</c:when>
            	<c:otherwise>
            		<div class="table-responsive">
		                <table class="table table-bordered">
		                    <thead>
		                        <tr>
		                            <th scope="col">Título</th>
		                            <th scope="col">Precio</th>
		                            <th scope="col">Autor</th>
		                            <th scope="col">Género</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<c:forEach var="document" items="${documents}">
		                    		<tr>
			                            <th scope="row">
			                                <a href="<c:url value="/detail/${document.id}" />">
			                                    ${document.title}
			                                </a>
			                            </th>
			                            <td>$${document.price}</td>
			                            <td>${document.author.fullName}</td>
			                            <td>${document.genre.name}</td>
			                        </tr>
		                    	</c:forEach>
		                    </tbody>
		                </table>
		            </div>
            	</c:otherwise>
            </c:choose>
        </t:main_content>
    </jsp:body>
</t:layout>
