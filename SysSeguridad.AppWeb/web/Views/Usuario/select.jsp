<%-- 
    Document   : select
    Created on : 24 ago. 2023, 19:44:08
    Author     : Not4l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<%@page import="sysseguridad.accesoadatos.UsuarioDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Usuario> usuarios = UsuarioDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slUsuario" name="idUsuario">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Usuario user : usuarios) {%>
    <option <%=(id == user.getId()) ? "selected" : ""%>  value="<%=user.getId()%>"><%= user.getNombre()%></option>
    <%}%>
</select>
<label for="idusuario">Usuario</label>

