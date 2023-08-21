/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sysseguridad.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import sysseguridad.entidadesdenegocio.Machine;
import sysseguridad.accesoadatos.newMachineDAL;
import sysseguridad.accesoadatos.UsuarioDAL;
import sysseguridad.entidadesdenegocio.Usuario;
import sysseguridad.appweb.utils.*;

/**
 *
 * @author Not4l
 */
@WebServlet(name = "newMachineServlet", urlPatterns = {"/newMachineServlet"})
public class newMachineServlet extends HttpServlet {

    private Machine getMachine(HttpServletRequest request) {
        String action = Utilidad.getParameter(request, "action", "index");
        Machine machine = new Machine();
        machine.setMachinesName(Utilidad.getParameter(request, "Machines_Name", ""));
        machine.setBrand(Utilidad.getParameter(request, "Brand", ""));
        machine.setSerialNumber(Integer.parseInt(Utilidad.getParameter(request, "Serial_Number", "0")));
        machine.setAcquisitionDate(Utilidad.getParameter(request, "Acquisition_Date", ""));
        machine.setMaintenanceDate(Utilidad.getParameter(request, "Maintenance_Date", ""));
        machine.setNextMaintenanceDate(Utilidad.getParameter(request, "Maintenance_Date", ""));
        machine.setEstatus(Byte.parseByte(Utilidad.getParameter(request, "Estatus", "0")));

        if (action.equals("index")) {
            machine.setTopAux(Integer.parseInt(Utilidad.getParameter(request, "Top_Aux", "10")));
            machine.setTopAux(machine.getTopAux() == 0 ? Integer.MAX_VALUE : machine.getTopAux());
        } else {
            machine.setIdMachines(Integer.parseInt(Utilidad.getParameter(request, "Id_Machines", "0")));

        }
        return machine;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = new Machine();
            machine.setTopAux(10);
            ArrayList<Machine> machines = newMachineDAL.searchIncludeUsuario(machine);
            //en esta linea probablemente se traiga el valor de una variable que se definira posteirormente en la vista/ jsp
            request.setAttribute("machines", machines);
            request.setAttribute("Top_Aux", machine.getTopAux());
            request.getRequestDispatcher("VIews/Machine/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            ArrayList<Machine> machines = newMachineDAL.searchIncludeUsuario(machine);
            request.setAttribute("machines", machines);
            request.setAttribute("Top_Aux", machine.getTopAux());
            request.getRequestDispatcher("Views/Machine/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Machine/create.jsp").forward(request, response);
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            int result = newMachineDAL.create(machine);
            if (result != 0) {
                request.setAttribute("action", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            Machine machineResult = newMachineDAL.getById(machine);
            if (machineResult.getIdMachines() > 0) {
                Usuario usuario = new Usuario();
                usuario.setId(machineResult.getIdMachines());
                machineResult.setUsuario(UsuarioDAL.obtenerPorId(usuario));
                request.setAttribute("machine", machineResult);
            } else {
                Utilidad.enviarError("El id: " + machineResult.getIdMachines() + "no existe en la tabla de Maquinaria", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Machine/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine macihne = getMachine(request);
            int result = newMachineDAL.modify(macihne);
            if (result != 0) {
                request.setAttribute("action", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Machine/details.jsp");
    }

    private void doGetRequsetDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Machine/delete.jsp");
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            int result = newMachineDAL.delete(machine);
            if (result != 0) {
                request.setAttribute("action", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se pudo eliminar maquinaria del registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Utilidad.getParameter(request, "action", "index");

        try {
            switch (action) {
                case "index":
                    request.setAttribute("action", action);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("action", action);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("action", action);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("action", action);
                    doGetRequsetDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("action", action);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("action", action);
                    doGetRequestIndex(request, response);
            }
        } catch (ServletException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "303\nLa pagina no fue encontrada");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Utilidad.getParameter(request, "action", "index");

        try {
            switch (action) {
                case "index":
                    request.setAttribute("action", action);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("action", action);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("action", action);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("action", action);
                    doPostRequestDelete(request, response);
                    break;
            }
        } catch (ServletException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "303\nLapagina no fue encontrada");
        }
    }
}
