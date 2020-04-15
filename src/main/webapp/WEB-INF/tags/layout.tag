<%-- 
    Document   : layout
    Created on : 25/03/2020, 12:59:27 AM
    Author     : hsanchez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="Common page layout" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true"
	description="The current page title"%>
<%@attribute name="back" required="false" fragment="true"%>
<%@attribute name="scripts" required="false" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous" />
	<link rel="stylesheet"
		href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
	<link rel="stylesheet" href="${ctx}/css/index.css" />
	<title>Biblioteca Digital | ${title}</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary">
		<span class="navbar-brand mb-0 h1"> ${title} </span>
	</nav>

	<main class="container pt-2">
		<c:if test="${back != null}">
			<div class="row px-0 pb-2">
				<div class="col">
					<jsp:invoke fragment="back" />
				</div>
			</div>
		</c:if>
		<jsp:doBody />
	</main>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
		
	<jsp:invoke fragment="scripts" />
</body>
</html>
