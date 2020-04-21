<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	        		<c:if test="${isLoggedin}">
	        			<div
							class="d-flex align-items-center justify-content-end mb-2">
	        				<a
								href='<c:url value="/document/update/detail/${document.id}"/>'
								class="btn btn-outline-info mr-2">
	        					<i class="las la-edit"></i> Editar
	        				</a>        				
	        				<a
								href='<c:url value="/document/delete/confirm/${document.id}" />'
								class="btn btn-outline-danger">
	        					<i class="las la-trash"></i> Eliminar
	        				</a>
	        			</div>
	        		</c:if>
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
		                            	<img class="img-thumbnail portrait"
										src="data:image/png;base64,${document.thumbnailUrl}"
										alt="Portada del documento" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">Precio</th>
		                            <td>
		                            	<fmt:setLocale value="en_US" />
		                            	<fmt:formatNumber
											value="${document.price}" type="currency" />
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
			const back = document.getElementById("back");
			const referrer = document.referrer;
			const sources = ["/search_results","/document/update/search/results","/document/update/search/results"];
			const isFromSearch = sources.some(s => referrer.includes(s));
			
			back.setAttribute("href", isFromSearch ? referrer : "/digital_library/search");
		</script>
    </jsp:body>
</t:layout>