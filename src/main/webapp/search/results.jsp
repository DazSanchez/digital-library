<%-- 
    Document   : results
    Created on : 25/03/2020, 12:51:45 AM
    Author     : hsanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                        <tr>
                            <th scope="row">
                                <a href="<c:url value="/detail/1" />">
                                    New Title
                                </a>
                            </th>
                            <td>$200.00</td>
                            <td>Otto Lorem</td>
                            <td>T.I.</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </t:main_content>
    </jsp:body>
</t:layout>
