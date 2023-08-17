<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Error de la aplicación</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main class="container">
            <div class="row">
                <h4>Ha ocurrido el siguiente error en la aplicación:</h4>
                <p>Mensaje: ${exception.message}</p>
                <p>Causa: ${exception.cause}</p>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
