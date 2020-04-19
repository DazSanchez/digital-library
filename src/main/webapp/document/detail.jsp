<%-- 
    Document   : detail
    Created on : 25/03/2020, 02:15:29 AM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout title="Detalle de documento">
	<jsp:attribute name="back">
        <c:if test="${document != null}">
        	<a class="btn-sm" href="#" id="back">
	            &#11104; Volver a la búsqueda
	        </a>
        </c:if>
    </jsp:attribute>

	<jsp:body>
        <t:main_content>
        	<c:if test="${error != null}">
        		<div class="alert alert-danger">
        			${error}
        		</div>
        	</c:if>
        	<c:choose>
        		<c:when test="${document != null}">
		            <div class="table-responsive">
		                <table class="table table-bordered">
		                    <tbody>
		                        <tr>
		                            <th scope="row">Título</th>
		                            <td>${document.title}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Portada</th>
		                            <td>
		                            	<img src="data:image/png;base64,${document.thumbnailUrl}" alt="Portada del documento" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Precio</th>
		                            <td>
		                            	<fmt:setLocale value="en_US"/>
		                            	<fmt:formatNumber value="${document.price}" type="currency" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Número de páginas</th>
		                            <td>${document.pageNumber}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Autor</th>
		                            <td>${document.author.fullName}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Género</th>
		                            <td>${document.genre}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Formato</th>
		                            <td>${document.format}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Tiempo de entrega</th>
		                            <td>${document.deliveryTime}</td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Editorial</th>
		                            <td>${document.editorial}</td>
		                        </tr>
		                    </tbody>
		                </table>
		            </div>
	            </c:when>
	            <c:otherwise>
	            	<div class="alert alert-info">
	            		No existe algún documento con el id: ${documentId}
	            	</div>
	            	<div class="d-flex justify-content-end align-items-center">
						<a href='<c:url value="/search" />'>Ir a la búsqueda</a>
		        	</div>
	            </c:otherwise>
        	</c:choose>
        </t:main_content>

        <script>
			document.getElementById("back").setAttribute("href",document.referrer);
		</script>
    </jsp:body>
</t:layout>