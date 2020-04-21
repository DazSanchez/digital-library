<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Inicio de sesión">
    <jsp:body>
        <div class="w-100 d-flex justify-content-center">
            <div class="small-container">
                <t:main_content>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    <form action="<c:url value="/login" context="${ctx}" />" method="POST">
                        <div class="row">
                            <div class="form-group col-12">
                                <label for="username">Usuario</label>
                                <input type="text" class="form-control" id="username" name="username" required placeholder="Nombre de usuario">
                            </div>
                            <div class="form-group col-12">
                                <label for="password">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required placeholder="Contraseña">
                            </div>
                            <div class="d-flex col-12 justify-content-between align-items-center">
                                <a href="<c:url value="/" />">
                                    Volver al inicio
                                </a>
                                <button class="btn btn-primary">
                                    Ingresar
                                </button>
                            </div>
                        </div>
                    </form>
                </t:main_content>
            </div>
        </div>
    </jsp:body>
</t:layout>