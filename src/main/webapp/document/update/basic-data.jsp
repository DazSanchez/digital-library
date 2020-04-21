<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Actualización de información básica">
	<jsp:body>
        <t:main_content>
        	<p class="h5">Información básica del documento</p>
        	
        	<c:if test="${hasErrors}">
    			<div class="alert alert-danger">
    				<c:forEach items="${errors}" var="error">
    					<p>${error}</p>
    				</c:forEach>
    			</div>
    		</c:if>
        	
        	<form method="POST" enctype="multipart/form-data">
        		<div class="form-group">
			    	<label for="title">Título<span class="text-danger">*</span></label>
			    	<input type="text" class="form-control" id="title"
						required="required" name="title" value="${document.title}" />
				</div>
				
				<label for="price">Precio<span class="text-danger">*</span></label>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">$</span>
					</div>
					<input id="price" type="number" class="form-control"
						placeholder="0" aria-label="Username"
						aria-describedby="basic-addon1" required="required" name="price"
						value="${document.price}" />
				</div>
				
				<div class="form-group">
			    	<label for="pageNumber">Número de páginas<span
						class="text-danger">*</span></label>
			    	<input id="pageNumber" type="number" class="form-control"
						required="required" name="pageNumber"
						value="${document.pageNumber}" />
				</div>
				
				<div class="form-group">
				    <label for="format">Formato</label>
				    <select class="form-control" id="format" required="required"
						name="format">
						<c:if test="${formats != null}">
							<c:forEach items="${formats}" var="format">
								<c:choose>
									<c:when test="${document.format.id == format.id}">
										<option value="${format.id}" selected="selected">
											${format.name}
										</option>
									</c:when>
									<c:otherwise>
										<option value="${format.id}">
											${format.name}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>										
						</c:if>
				    </select>
				</div>
				
				<div class="form-group">
				    <label for="documentType">Tipo de documento</label>
				    <select class="form-control" id="documentType"
						required="required" name="documentType">
				    	<c:if test="${documentTypes != null}">
							<c:forEach items="${documentTypes}" var="documentType">
								<c:choose>
									<c:when test="${document.documentType.id == documentType.id}">
										<option value="${documentType.id}" selected="selected">
											${documentType.name}
										</option>
									</c:when>
									<c:otherwise>
										<option value="${documentType.id}">
											${documentType.name}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>										
						</c:if>
				    </select>
				</div>
				
				<div class="form-group">
				    <label for="thumbnail">Imagen de portada</label>
				    <input type="file" class="form-control-file" id="thumbnail"
						name="thumbnail" accept="image/*">
					<small id="help" class="form-text text-muted">
						No seleccione un archivo nuevo si desea mantener la imagen anterior.
					</small>
			    </div>
				
				<label for="time">
					Tiempo de entrega
					<span class="text-danger">*</span>
				</label>
				<div class="form-row">
					<div class="col">
						<div class="form-group">
					    	<input placeholder="1" id="time" type="number"
								class="form-control" required="required" name="deliveryTime"
								value="${document.deliveryTime.time}" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
					    	<input placeholder="día" type="text" class="form-control"
								id="unit" required="required" name="timeUnit"
								value="${document.deliveryTime.unit}" />
						</div>
					</div>
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
