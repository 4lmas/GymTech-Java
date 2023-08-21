package sysseguridad.appweb.controllers;

import sysseguridad.accesoadatos.CustomException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import sysseguridad.accesoadatos.MachineDAL;
import sysseguridad.entidadesdenegocio.Machine;
import sysseguridad.appweb.utils.Utilidad;
import sysseguridad.accesoadatos.CustomException;

import sysseguridad.appweb.utils.*;

/**
 *
 * @author Not4lmas
 */
@WebServlet(name = "SvMachineController", urlPatterns = {"/SvMachineController"})
public class SvMachineController extends HttpServlet {

    /*!IMPORTANTE TERMINAR LAS VISTAS DE PRINCIPALES CON LOS IDES/NAMES/INPUTS PARA 
    * PODER LLAMAR TODO DESDE AQUI
    */
    
    /**
     * REMPLAZANDO NOMBRE DE VARIALES:
     * accion : action
     */
    private Machine getMachine(HttpServletRequest request){
       
       String action = Utilidad.getParameter(request, "accion", "index");
       Machine machine = new Machine();
       if(action.equals("create") == false){
           machine.setIdMachines(Integer.parseInt(Utilidad.getParameter(request, "Id_Machine", "0")));
           machine.setTopAux(machine.getTopAux() == 0 ? Integer.MAX_VALUE : machine.getTopAux());
       }
       return machine;
    }
    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Machine, y el parámetro accion sea igual index. Este método se encargara de
     * enviar los datos de machine al jsp de index de Mahine.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Machine
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet MAchine que utlizaremos para enviar el jsp
     * @throws javax.servlet.ServletException, este es un manejador de errores de los servlets
     * @throws java.io.IOException este es un manejador de errores pero del jdk de java.
     */
    
    /***
     *@throws MachineDAl da error ya que no se ha añadido el archivo DAl de machine
     * encaso de estar en ingles/español se tendra que cambiar el nombre de los
     * @param metodos/parametros para poder solucionar los conflictos
     */
    
    @SuppressWarnings("Errores de escritura posibles por el lenguaje que se resolveran despues")
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Machine machine = new Machine(); 
            machine.setTopAux(10);
            ArrayList<Machine> machines = MachineDAL.buscarMachine(machine);
            request.setAttribute("machines", machines);
            request.setAttribute("Top_Aux", machine.getTopAux());
            request.getRequestDispatcher("Views/Machine/index.jsp").forward(request, response);
            
        } catch (Exception e){
            CustomException customEx = new CustomException("Ocurrio el siguiente error: " , e);
            Utilidad.enviarError("error",request, response);
        }
    }
    
        private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request); // Llenar la instancia de Rol con los parámetros enviados en el request 
            ArrayList<Machine> machines= MachineDAL.buscarMachine(machine); // Buscar los roles que cumple con los datos enviados en el request
            request.setAttribute("machines", machines); // Enviar los roles al jsp utilizando el request.setAttribute con el nombre del atributo roles
            // Enviar el Top_aux de Rol al jsp utilizando el request.setAttribute con el nombre del atributo top_aux
            request.setAttribute("Top_Aux", machine.getTopAux());
            request.getRequestDispatcher("Views/Machine/index.jsp").forward(request, response); // Direccionar al jsp index de Rol
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       request.getRequestDispatcher("Views/Machine/create.jsp").forward(request, response);
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Machine , y el parámetro accion sea igual create.
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            int result = MachineDAL.crear(machine);
            if (result != 0){
                request.setAttribute("action", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("error",request, response);
            }
        } catch (Exception er){   
           Utilidad.enviarError("error",request, response);
        }
    }
    
    /**
     * En este método obtiene por id machine desde la capa de acceso a datos el
     * id se captura del request que se envio al servlet de Machine
     */
    
    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Machine machine = getMachine(request);
            Machine machine_result = MachineDAL.getById(machine);
            if(machine_result.getIdMachines() > 0){
                request.setAttribute("machine", machine_result);
            } else {
                 Utilidad.enviarError("El Id:" + machine.getIdMachines() + " no existe en la tabla de Rol", request, response);
            }
        } catch (Exception er) {
            Utilidad.enviarError(er.getMessage(), request, response);
        }
    }
    

    
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el rol al jsp de edit que se obtiene por Id
        requestGetById(request, response);
        // Direccionar al jsp edit de Rol
        request.getRequestDispatcher("Views/Machine/edit.jsp").forward(request, response);
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Machine , y el parámetro accion sea igual edit.
     */
    
    private void doPostRequestEdit (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            int result = MachineDAL.modify(machine);
            
            if(result != 0) {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Machine , y el parámetro accion sea igual details.
     */
    
    private void doGetRequestDetails (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Machine/details.jsp");
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Machine , y el parámetro accion sea igual delete.
     */
    
    private void doGetRequestDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Machine/delete.jsp");
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Machine , y el parámetro accion sea igual delete.
     */
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Machine machine = getMachine(request);
            int result = MachineDAL.delete(machine);
            
            if(result != 0){
                request.setAttribute("action", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception er){
            Utilidad.enviarError(er.getMessage(), request, response);
        }
    }
    
    // </------------------------- doGet y doPost sobre escritos ------------------------->//
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    /**
     * Estos son métodos override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Machine
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     * @param lambda ()  {} similar a funcion anonima de JavaScript
     */
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         SessionUser.authorize(request, response, () -> {
            String action = Utilidad.getParameter(request, "accion", "index"); // Obtener el parámetro accion del request
            
            /* Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            se envian los atributos a cada una de estas direcciones en el jsp
            Despues hace un metodo de r al método doGetRequestIndex.*/
            
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
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("action", action);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("action", action);
                    doGetRequestIndex(request, response);
            }
        });
    }
    
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        SessionUser.authorize(request, response, () -> {
            String action = Utilidad.getParameter(request, "accion", "index");
            
            switch (action) {
                case "index":
                    request.setAttribute("accion", action);
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
                default:
                    request.setAttribute("action", action);
                    doGetRequestIndex(request, response);
            }
        });
    }
}