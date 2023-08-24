<%-- 
    Document   : delete
    Created on : 22 ago. 2023, 13:46:11
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

<!-- 
<html>
    <head>        
<jsp:include page="/Views/Shared/title.jsp" />
<title>Eliminar Usuario</title>
</head>
<body>
<jsp:include page="/Views/Shared/headerBody.jsp" />  
<main class="container">   
    <h5>Eliminar Usuario</h5>
    <form action="Usuario" method="post">  
        <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
        <input type="hidden" name="id" value="<%=machines.getIdMachines()%>">  
        <div class="row">
            <div class="input-field col l4 s12">
                <input  id="txtNombre" type="text" value="<%=usuario.getNombre()%>" disabled>
                <label for="txtNombre">Nombre</label>
            </div>                       
            <div class="input-field col l4 s12">
                <input  id="txtApellido" type="text" value="<%=usuario.getApellido()%>" disabled>
                <label for="txtApellido">Apellido</label>
            </div> 
            <div class="input-field col l4 s12">
                <input  id="txtLogin" type="text" value="<%=usuario.getLogin()%>" disabled>
                <label for="txtLogin">Login</label>
            </div>                     
            <div class="input-field col l4 s12">   
                <select id="slEstatus" name="estatus" disabled>
                    <option value="0" <%=(usuario.getEstatus() == 10) ? "selected" : ""%>>SELECCIONAR</option>
                    <option value="<%=Usuario.EstatusUsuario.ACTIVO%>"  <%=(usuario.getEstatus() == Usuario.EstatusUsuario.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                    <option value="<%=Usuario.EstatusUsuario.INACTIVO%>"  <%=(usuario.getEstatus() == Usuario.EstatusUsuario.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                </select>       
                <label for="slEstatus">Estatus</label>                       
            </div>
            <div class="input-field col l4 s12">
                <input id="txtRol" type="text" value="<%=usuario.getRol().getNombre()%>" disabled>
                <label for="txtRol">Rol</label>
            </div> 
        </div>
        <div class="row">
            <div class="col l12 s12">
                <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                <a href="Usuario" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
            </div>
        </div>
    </form>          
</main>
<jsp:include page="/Views/Shared/footerBody.jsp" />         
</body>
</html>
-->