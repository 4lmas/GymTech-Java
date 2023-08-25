<%-- 
    Document   : edit
    Created on : 22 ago. 2023, 13:46:49
    Author     : Not4l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<%@page import="sysseguridad.entidadesdenegocio.Machine"%>
<% Machine machine = (Machine) request.getAttribute("machine");%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp"/>

        <main class="container">
            <h5>Editar Maquinaria</h5>

            <form action="Machine" method="POST" onsubmit="return validarFormulario()">
                <input type="hidden" name="action" value="<%=request.getAttribute("action")%>">
                <input type="hidden" name="IdMachines" value="<%=machine.getIdMachines()%>">

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
                        <select id="slEstatus" name="estatus" class="validate">
                            <option value="0" <%=(machine.getStatus() == 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Machine.statusMachine.ACTIVO%>"  <%=(machine.getStatus() == Machine.statusMachine.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Machine.statusMachine.INACTIVO%>"  <%=(machine.getStatus() == Machine.statusMachine.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>       
                        <label for="slEstatus">Estado</label>
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
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
                        <buttton type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Machine" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />    
        <script>
            function validarFormulario() {
                var result = true;
                var slEstatus = document.getElementById("slStatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slUsuario = document.getElementById("slUsuario");
                var slUsuario_error = document.getElementById("slUsuario_error");
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slUsuario.value == 0) {
                    slUsuario_error.innerHTML = "El Rol es obligatorio";
                    result = false;
                } else {
                    slUsuaario_error.innerHTML = "";
                }
                return result;
            }
        </script>
    </body>
</html>
