<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:layout title="Panel de administrador">
	<jsp:attribute name="scripts">
        <script src='<c:url value="/js/createDocument.js" />'></script>
    </jsp:attribute>

	<jsp:body>
    	<t:main_content>
    		<p class="h4">Agregar un documento nuevo</p>
    		<p class="text-danger">Los campos marcados con * son requeridos.</p>
    		
    		<c:if test="${hasErrors}">
    			<div class="alert alert-danger">
    				<c:forEach items="${errors}" var="error">
    					<p>${error}</p>
    				</c:forEach>
    			</div>
    		</c:if>
    		
    		<form action='<c:url value="/document/create" />'
				id="documentForm" method="post" class="mt-2"
				enctype="multipart/form-data">
	    		<div class="accordion" id="accordion">
	    			<div class="card">
	    				<div class="card-header" id="basicInfoHeader">
	    					Agregue información básica
	    				</div>
	    				<div id="basicInfo" class="collapse show"
							aria-labelledby="basicInfoHeader" data-parent="#accordion">
	    					<div class="card-body">
    							<div class="form-group">
							    	<label for="title">Título<span class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="title"
										required="required" name="title" />
								</div>
								
								<label for="price">Precio<span class="text-danger">*</span></label>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">$</span>
									</div>
									<input id="price" type="number" class="form-control"
										placeholder="0" aria-label="Username"
										aria-describedby="basic-addon1" required="required"
										name="price" />
								</div>
								
								<div class="form-group">
							    	<label for="pageNumber">Número de páginas<span
										class="text-danger">*</span></label>
							    	<input id="pageNumber" type="number" class="form-control"
										required="required" name="pageNumber" />
								</div>
								
								<div class="form-group">
								    <label for="format">Formato</label>
								    <select class="form-control" id="format" required="required"
										name="format">
										<c:if test="${formats != null}">
											<c:forEach items="${formats}" var="format">
												<option value="${format.id}">${format.name}</option>
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
												<option value="${documentType.id}">${documentType.name}</option>
											</c:forEach>										
										</c:if>
								    </select>
								</div>
								
								<div class="form-group">
								    <label for="thumbnail">Imagen de portada<span
										class="text-danger">*</span></label>
								    <input type="file" class="form-control-file" id="thumbnail"
										name="thumbnail" required="required" accept="image/*">
							    </div>
								
								<label for="time">
									Tiempo de entrega
									<span class="text-danger">*</span>
								</label>
								<div class="form-row">
									<div class="col">
										<div class="form-group">
									    	<input placeholder="1" id="time" type="number"
												class="form-control" required="required" name="deliveryTime" />
										</div>
									</div>
									<div class="col">
										<div class="form-group">
									    	<input placeholder="día" type="text" class="form-control"
												id="unit" required="required" name="timeUnit" />
										</div>
									</div>
								</div>
	    					</div>
	    				</div>
	    			</div>
	    			
	    			<div class="card">
	    				<div class="card-header" id="authorInfoHeader">
	    					Agregue información del autor
	    				</div>
	    				<div id="authorInfo" class="collapse show"
							aria-labelledby="authorInfoHeader" data-parent="#accordion">
	    					<div class="card-body">
								<div class="form-group">
							    	<label for="name">Nombre(s)<span class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="name"
										required="required" name="authorName" />
								</div>
								<div class="form-group">
							    	<label for="firstSurname">Apellido paterno<span
										class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="firstSurname"
										required="required" name="authorFirstName" />
								</div>
								<div class="form-group">
							    	<label for="secondSurname">Apellido materno<span
										class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="secondSurname"
										required="required" name="authorSecondName" />
								</div>
	    					</div>
	    				</div>
	    			</div>
	    			
	    			<div class="card">
	    				<div class="card-header" id="genreInfoHeader">
	    					Agregar información del género literario
	    				</div>
	    				<div id="genreInfo" class="collapse show"
							aria-labelledby="genreInfoHeader" data-parent="#accordion">
	    					<div class="card-body">
	    						<div class="form-group">
							    	<label for="genre">Nombre del género<span
										class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="genre"
										required="required" placeholder="Historia" name="genre" />
								</div>
	    					</div>
	    				</div>
	    			</div>
	    			
	    			<div class="card">
	    				<div class="card-header" id="editorialInfoHeader">
	    					Agregue información sobre la editorial
	    				</div>
	    				<div id="editorialInfo" class="collapse show"
							aria-labelledby="editorialInfoHeader" data-parent="#accordion">
	    					<div class="card-body">
	    						<div class="form-group">
							    	<label for="editorial">Nombre de la editorial<span
										class="text-danger">*</span></label>
							    	<input type="text" class="form-control" id="editorial"
										required="required" placeholder="Grupo Planeta"
										name="editorial" />
								</div>
	    						
	    						<div class="d-flex justify-content-end">
									<button type="submit" class="btn btn-primary">
										Guardar
									</button>
								</div>
	    					</div>
	    				</div>
	    			</div>
	    		</div>
    		</form>
    	</t:main_content>
    </jsp:body>
</t:layout>