<%-- 
    Document   : layout
    Created on : 25/03/2020, 12:59:27 AM
    Author     : hsanchez
--%>

<%@tag description="Common page layout" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" description="The current page title"%>
<%@attribute name="back" required="false" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
            />
        <link rel="stylesheet" href="${ctx}/css/index.css" />
        <title>Biblioteca Digital | ${title}</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <span class="navbar-brand mb-0 h1">
                ${title}
            </span>
        </nav>

        <main class="container pt-2">
            <div class="row px-0 pb-2">
                <div class="col">
                    <jsp:invoke fragment="back" />
                </div>
            </div>
            <jsp:doBody />
        </main>
    </body>
</html>
