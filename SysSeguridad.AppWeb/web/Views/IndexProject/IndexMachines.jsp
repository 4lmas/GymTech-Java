<%-- 
    Document   : IndexMachines
    Created on : 9 ago. 2023, 20:12:58
    Author     : Not4l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./WEB-STYLE/IndexMachines.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <header>
        <div class="p-3 mb-2 bg-danger text-white">
            <nav class="navbar navbar-expand-lg">
                <div class="container-fluid .bg-danger">
                    <a class="navbar-brand" href="Principal.html">HOME</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link active" aria-current="page" href="#">Usuario</a>
                            <a class="nav-link" href="#">Roles</a>
                            <a class="nav-link" href="Maquinaria.html">Maquinaria</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <body>
        <br>
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
        <br>
        <%-- Agrega este bloque para mostrar el mensaje de error --%>
        <% if (request.getAttribute("errorMessage") != null) {%>
        <div class="alert alert-danger" role="alert">
            <%= request.getAttribute("errorMessage")%>
        </div>
        <% }%>
    </body>
</html>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
