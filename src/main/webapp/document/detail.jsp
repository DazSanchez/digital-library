<%-- 
    Document   : detail
    Created on : 25/03/2020, 02:15:29 AM
    Author     : hsanchez <hsanchez.dev@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:layout title="Detalle de documento">
    <jsp:attribute name="back">
        <a class="btn-sm" href="#" id="back">
            &#11104; Volver a la búsqueda
        </a>
    </jsp:attribute>

    <jsp:body>
        <t:main_content>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th scope="row">Título</th>
                            <td>New title</td>
                        </tr>
                        <tr>
                            <th scope="row">Precio</th>
                            <td>$200.00</td>
                        </tr>
                        <tr>
                            <th scope="row">Autor</th>
                            <td>Otto lorem</td>
                        </tr>
                        <tr>
                            <th scope="row">Género</th>
                            <td>Noticias</td>
                        </tr>
                        <tr>
                            <th scope="row">Formato</th>
                            <td>Impreso</td>
                        </tr>
                        <tr>
                            <th scope="row">Tiempo de entrega</th>
                            <td>2 días</td>
                        </tr>
                        <tr>
                            <th scope="row">Editorial</th>
                            <td>Lumbardo</td>
                        </tr>
                        <tr>
                            <th scope="row">Número de páginas</th>
                            <td>20</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </t:main_content>

        <script>
            document.getElementById("back").setAttribute("href", document.referrer);
        </script>
    </jsp:body>
</t:layout>