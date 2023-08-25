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
    ArrayList<Machine> machines = (ArrayList<Machine>) request.getAttribute("machines");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;

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
        <title>Maquinaria</title>
        <jsp:include page="/Views/Shared/title.jsp" />
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main class="container">
            <h5>Buscar maquinaria</h5>
            <form action="Machine" method="POST">
                <input type="hidden" name="action" value="<%= request.getAttribute("action")%>">
                <div class="row">
                    <div class="input-field col s12 m6">
                        <input id="txtName" type="text" name="Name">
                        <label for="txtName">Nombre</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input  id="Brand" type="text" name="Brand">
                        <label for="Brand">Marca</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <select id="slStatus" name="Status">
                            <option value="0">Seleccionar</option>
                            <option value="<%=Machine.statusMachine.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Machine.statusMachine.INACTIVO%>">INACTIVO</option>
                        </select>
                        <label for="slStatus">Status</label>
                    </div>
                    <div class="input-field col s12">
                        <jsp:include page="/Views/Usuario/select.jsp">
                            <jsp:param name="id" value="0" />
                        </jsp:include>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="Top_Aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">search</i>Buscar
                        </button>
                        <a href="Machine?action=create" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">add</i>Crear
                        </a>
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Nombre</th>  
                                    <th>Marca</th> 
                                    <th>Numero de seria</th>  
                                    <th>Estado</th>  
                                    <th>Fecha de compra</th>   
                                    <th>Dia de mantenimiento</th>   
                                    <th>Siguiente mantenimiento</th>
                                    <th>Encargado del mantenimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Machine machine : machines) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                        String estatus = "";
                                        switch (machine.getStatus()) {
                                            case Machine.statusMachine.ACTIVO:
                                                estatus = "ACTIVO";
                                                break;
                                            case Machine.statusMachine.INACTIVO:
                                                estatus = "INACTIVO";
                                                break;
                                            default:
                                                estatus = "";
                                        }%>

                                <tr data-page="<%= tempNumPage%>">
                                    <td><%=machine.getMachinesName()%></td>  
                                    <td><%=machine.getBrand()%></td>
                                    <td><%=machine.getSerialNumber()%></td>  
                                    <td><%=estatus%></td>
                                    <td><%=machine.getAcquisitionDate()%></td> 
                                    <td><%=machine.getMaintenanceDate()%></td>
                                    <td><%=machine.getNextMaintenanceDate()%></td>
                                    <td><%=machine.getUsuario().getNombre()%></td> 
                                    <td>
                                        <div style="display:flex">
                                            <a href="newMachineServlet?action=edit&id=<%=machine.getIdMachines()%>" title="Modificar" class="waves-effect waves-light btn green">
                                                <i class="material-icons">edit</i>
                                            </a>
                                            <a href="newMachineServlet?action=details&id=<%=machine.getIdMachines()%>" title="Ver" class="waves-effect waves-light btn blue">
                                                <i class="material-icons">description</i>
                                            </a>
                                            <a href="newMachineServlet?action=delete&id=<%=machine.getIdMachines()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                                <i class="material-icons">delete</i>
                                            </a>    
                                        </div>   
                                    </td>
                                </tr>
                            <tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
