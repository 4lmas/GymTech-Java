<!--
    Created on : 9 ago. 2023, 11:44:07
    Author     : Not4l
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.appweb.utils.*"%>
<% if (SessionUser.isAuth(request) == false) {
        response.sendRedirect("Usuario?accion=login");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="WEB-STYLE/index.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <jsp:include page="/Views/Shared/title.jsp" />
    </head>
    <body class="body-position">
        <jsp:include page="/Views/Shared/headerBody.jsp" />

        <!-- Cartas de Materialize -->

        <div class="main-container">
            <div class="card-group">
                <div class="card bg-danger">
                    <img src="https://tienda-sportfitness.com/wp-content/uploads/2018/04/71445-POLEA-DUAL-CON-SMITH-PESO-LIBRE-SPORT-FITNESS-300x300.webp" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">POLEA DUAL CON SMITH PESO LIBRE</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                        <button type="button" class="btn btn-outline-light"><a href="Maquinaria1.html">Detalle</a></button>
                    </div>
                </div>
                <div class="card bg-danger">
                    <img src="https://www.rocfit.com/wp-content/uploads/categoria-maquinas-musculacion-1.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">POLEA FUNCIONAL TRAINING</h5>
                        <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                        <button type="button" class="btn btn-outline-light"><a href="Maquinaria2.html">Detalle</a></button>
                    </div>
                </div>
                <div class="card bg-danger">
                    <img src="https://image.made-in-china.com/44f3j00bswiJZpMGhcP/Home-Gym-Multifunction-Maquina-Bearings-Smith-Life-Fitness-Cable-Machine-Equipment-Functional-with-Trainer-Heavy-Duty-Power-Cage.webp" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">MAQUINAS DE MUSCULACION</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                        <button type="button" class="btn btn-outline-light"><a href="Maquinaria3.html">Detalle</a></button>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/Views/Shared/footerBody.jsp" />

        <!-- Scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
