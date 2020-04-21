<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Actualización de documento">
	<jsp:body>
        <t:main_content>
        	<p class="h5">¿Qué información del documento desea modificar?</p>
        	
        	<div
				class="d-flex flex-column flex-md-row justify-content-center row px-2">
        		<a href='<c:url value="/document/basic-data/update" />'
					class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-primary shadow-sm">
	        		<span
					class="d-block card-body d-flex align-items-center justify-content-center flex-column">
	        			<i class="las la-book-open la-2x text-reset"></i>
	        			<span class="d-block h5 text-center text-reset">
	        				Información básica
	        			</span>
	        		</span>
	        	</a>
	        	
	        	<a href='<c:url value="/document/author/update" />'
					class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-primary shadow-sm">
	        		<span
					class="d-block card-body d-flex align-items-center justify-content-center flex-column">
	        			<i class="las la-user-tie la-2x text-reset"></i>
	        			<span class="d-block h5 text-center text-reset">
	        				Información del autor
	        			</span>
	        		</span>
	        	</a>
	        	
	        	<a href='<c:url value="/document/genre/update" />'
					class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-primary shadow-sm">
	        		<span
					class="d-block card-body d-flex align-items-center justify-content-center flex-column">
	        			<i class="las la-theater-masks la-2x text-reset"></i>
	        			<span class="d-block h5 text-center text-reset">
	        				Información del género
	        			</span>
	        		</span>
	        	</a>
	        	
	        	<a href='<c:url value="/document/editorial/update" />'
					class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-primary shadow-sm">
	        		<span
					class="d-block card-body d-flex align-items-center justify-content-center flex-column">
	        			<i class="las la-school la-2x text-reset"></i>
	        			<span class="d-block h5 text-center text-reset">
	        				Información de la editorial
	        			</span>
	        		</span>
	        	</a>
        	</div>
        	<div class="d-flex justify-content-end mt-2">
        		<a class="btn btn-primary"
					href='<c:url value="/document/detail/${documentId}" />'>Terminar</a>
        	</div>
        </t:main_content>
    </jsp:body>
</t:layout>