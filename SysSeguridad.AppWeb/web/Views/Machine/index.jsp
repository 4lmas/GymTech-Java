<%--
    Document   : index
    Created on : 16 ago. 2023, 19:44:45
    Author     : Not4l
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<%@page import="sysseguridad.entidadesdenegocio.Machine"%>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<Machine> machines = (ArrayList<Machine>) request.getAttribute("usuarios");
    int numPage = 1;
    int numReg = 10;

    if (machines == null) {
        machines = new ArrayList<>();
    } else if (machines.size() > numReg) {
        double divNumPage = (double) machines.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_Aux = request.getParameter("Top_Aux");
    int top_aux = 10;
    if (strTop_Aux != null && strTop_Aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_Aux);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">
        <h5>Buscar maquinaria</h5>
        <form action="Machine" method="POST">
            <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
            <div class="row">
                <div class="input-field col s12 m6">
                    <input id="txtName" type="text" name="Name">
                    <label for="txtName">Nombre</label>
                </div>
                <div class="input-field col s12 m6">
                    <jsp:include page="/Views/Shared/selectTop.jsp">
                        <jsp:param name="Top_Aux" value="<%= top_aux %>" />
                    </jsp:include>
                </div>
            </div>
        </form>

            <a>hola</a>
        <div class="row">
            <div class="col s12">
                <div style="overflow: auto">
                    <table class="paginationjs">
                        <thead>
                            <tr>
                                <th>Nombre |</th>
                                <th>Marca|</th>
                                <th>Numero de serie |</th>
                                <th>Fecha de compra |</th>
                                <th>Dia de mantenimiento |</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int countReg = 0;
                                for (Machine machine : machines) {
                                    int tempNumPage = numPage;
                                    if (numPage > 1) {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                    }
                            %>
                            <tr data-page="<%= tempNumPage %>">
                                <td><%= machine.getMachinesName() %></td>
                                <td>
                                    <div style="display: flex">
                                        <a href="Machine?action=edit&id=<%= machine.getIdMachines() %>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">Editar</i>
                                        </a>
                                        <a href="Machine?action=details&id=<%= machine.getIdMachines() %>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="Machine?action=delete&id=<%= machine.getIdMachines() %>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <jsp:include page="/Views/Shared/paginacion.jsp">
                    <jsp:param name="numPage" value="<%= numPage %>" />
                </jsp:include>
            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>
