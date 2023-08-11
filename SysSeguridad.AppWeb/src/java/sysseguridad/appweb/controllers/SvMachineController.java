package sysseguridad.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
// import sysseguridad.accesoadatos.MachineDal;     esto se descomentara cuando la clase MachineDal este finalizada
import sysseguridad.entidadesdenegocio.Machine;
import sysseguridad.appweb.utils.*;

/**
 *
 * @author Not4l
 */
@WebServlet(name = "SvMachineController", urlPatterns = {"/SvMachineController"})
public class SvMachineController extends HttpServlet {

    /*!IMPORTANTE TERMINAR LAS VISTAS DE PRINCIPALES CON LOS IDES/NAMES/INPUTS PARA 
    * PODER LLAMAR TODO DESDE AQUI
    */
    private Machine getMachine(HttpServletRequest request){
        
    }
}

