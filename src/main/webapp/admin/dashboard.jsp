<%-- 
    Document   : dashboard
    Created on : 12/04/2020, 08:18:08 PM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<t:layout title="Panel de administrador">
    <jsp:body>
    	<div class="row align-items-center py-2">
    		<div class="col">
    			<p class="h5">Bienvenido, ${user.username}</p>
    		</div>
    		<div class="col text-right">
    			<a href='<c:url value="/logout" />' class="btn btn-outline-danger">
    				Cerrar sesión
    			</a>
    		</div>
    	</div>
    
        <div class="d-flex flex-column flex-md-row justify-content-center row px-2">
        	<a href='<c:url value="/search" />' class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-primary">
        		<span class="d-block card-body d-flex align-items-center justify-content-center flex-column">
        			<i class="las la-search la-3x text-reset"></i>
        			<span class="d-block h4 text-center text-reset">
        				Ir a la búsqueda
        			</span>
        		</span>
        	</a>
        	
        	<a href='<c:url value="/document/create" />' class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-success">
        		<span class="d-block card-body d-flex align-items-center justify-content-center flex-column">
        			<i class="las la-plus-circle la-3x text-reset"></i>
        			<span class="d-block h4 text-center text-reset">
        				Agregar un documento
        			</span>
        		</span>
        	</a>
        	
        	<a href='<c:url value="/search" />' class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-info">
        		<span class="d-block card-body d-flex align-items-center justify-content-center flex-column">
        			<i class="las la-edit la-3x text-reset"></i>
        			<span class="d-block h4 text-center text-reset">
        				Actualizar un documento
        			</span>
        		</span>
        	</a>
        	
        	<a href='<c:url value="/document/delete/search" />' class="d-block card col-12 col-md-6 col-lg-3 text-decoration-none text-reset hover-danger">
        		<span class="d-block card-body d-flex align-items-center justify-content-center flex-column">
        			<i class="las la-trash-alt la-3x"></i>
        			<span class="d-block h4 text-center">
        				Eliminar un documento
        			</span>
        		</span>
        	</a>
        </div>
    </jsp:body>
</t:layout>
