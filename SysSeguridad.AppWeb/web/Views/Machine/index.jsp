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
    int countReg = 0;
    if (machines == null) {
        machines = new ArrayList();
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
        <form action="Machine" method="POST">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
            <h1>insertar interfaz de maquinaria</h1>
            

            <jsp:include page="/Views/Shared/selectTop.jsp">
                <jsp:param name="Top_Aux" value="<%=top_aux%>" />                        
            </jsp:include>      
        </form>
    </body>
</html>
