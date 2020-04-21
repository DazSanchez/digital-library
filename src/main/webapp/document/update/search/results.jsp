<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:layout title="Actualizar un documento">
	<jsp:attribute name="back">
        <a class="btn-sm" href="<c:url value="/document/update/search" />">
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
		                        	<th scope="col">Portada</th>
		                            <th scope="col">Título</th>
		                            <th scope="col">Precio</th>
		                            <th scope="col">Autor</th>
		                            <th scope="col">Género</th>
		                            <th scope="col">Acciones</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<c:forEach var="document" items="${documents}">
		                    		<tr>
			                    		<td>
			                            	<img class="img-thumbnail portrait"
											src="data:image/png;base64,${document.thumbnailUrl}"
											alt="Portada del documento" />
			                            </td>
			                            <th scope="row">
			                                <a
											href="<c:url value="/document/detail/${document.id}" />">
			                                    ${document.title}
			                                </a>
			                            </th>
			                            <td>
			                            	<fmt:setLocale value="en_US" />
		                            		<fmt:formatNumber
												value="${document.price}" type="currency" />
			                            </td>
			                            <td>${document.author.fullName}</td>
			                            <td>${document.genre.name}</td>
			                            <td>
			                            	<a href='<c:url value="/document/update/detail/${document.id}" />' class="btn btn-outline-danger">
			                            		<i class="las la-edit"></i>
			                            	</a>
			                            </td>
			                        </tr>
		                    	</c:forEach>
		                    </tbody>
		                </table>
		            </div>
		            <div class='d-flex align-items-center justify-content-${hasPrev ? "between" : "end"} mt-2'>
		            	<c:if test="${hasPrev}">
		            		<a href='<c:url value="/document/update/search/results?type=${param.type}&title=${param.title}&page=${param.page - 1}&per_page=${param.per_page}" />' class="btn btn-link">
		            			<i class="las la-arrow-left"></i> Anterior
	            			</a>
		            	</c:if>
		            	<c:if test="${hasNext}">
			            	<a href='<c:url value="/document/update/search/results?type=${param.type}&title=${param.title}&page=${param.page + 1}&per_page=${param.per_page}" />' class="btn btn-link">
		            			Siguiente <i class="las la-arrow-right"></i>
	            			</a>
		            	</c:if>
		            </div>
            	</c:otherwise>
            </c:choose>
        </t:main_content>
    </jsp:body>
</t:layout>
