<%-- 
    Document   : delete
    Created on : 22 ago. 2023, 13:46:11
    Author     : Not4l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<%@page import="sysseguridad.entidadesdenegocio.Machine"%>
<% Machine machine = (Machine) request.getAttribute("machine");%>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<Machine> machines = (ArrayList<Machine>) request.getAttribute("machines");
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
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar maquina</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Eliminar Maquina</h5>
            <form action="action">
                <input type="hidden" name="action" value="<%=request.getAttribute("action")%>">
                <input type="hidden" name="idUsuario" value="<%=machine.getIdMachines()%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input id="txtMachinesName" name="machinesName" value="<%=machine.getMachinesName()%>">
                        <label for="txtMachinesName">Nombre de maquina</label>
                    </div>

                    <div class="input-field col l4 s12">
                        <input  id="txtBrand" type="text" name="brand" value="<%=machine.getBrand()%>" required class="validate" maxlength="30">
                        <label for="txtBrand">Marca</label>
                    </div>                       

                    <div class="input-field col l4 s12">
                        <input  id="txtSerialNumber" type="text" name="serialNumber" value="<%=machine.getSerialNumber()%>" required class="validate" maxlength="30">
                        <label for="txtSerialNumber">Numero de Serie</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtLogin" type="text" name="login" value="<%=machine.getStatus()%>" required  class="validate" maxlength="25">
                        <label for="txtLogin">Login</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  id="txtAcquisitionDate" type="text" name="acquisitionDAte" value="<%=machine.getAcquisitionDate()%>" required class="validate" maxlength="30">
                        <label for="txtAcquisitionDate">fecha de compra</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtMaintenanceDate" type="text" name="maintenanceDAte" value="<%=machine.getMaintenanceDate()%>" required class="validate" maxlength="30">
                        <label for="txtMaintenanceDate">Fecha de mantenimiento</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtNextMaintenance" type="text" name="nextMaintenance" value="<%=machine.getMaintenanceDate()%>" required class="validate" maxlength="30">
                        <label for="txtMaintenanceDate">Fecha de mantenimiento</label>
                    </div>   
                    <div class="input-field col l4 s12">
                        <jsp:include page="/Views/Usuario/select.jsp">
                            <jsp:param name="id" value="<%=machine.getIdUsuario()%>"/>
                        </jsp:include>
                        <span id="slUsuario_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Machine" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>
        </main>
    </body>
</html>