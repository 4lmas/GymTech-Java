/**
 *
 * @author Not4l
 */
package sysseguridad.accesoadatos;

import sysseguridad.entidadesdenegocio.*;
import java.util.*;
import java.time.LocalDate;

public class MachinesDal {

    /**
     * @param throws es para inicializar el manejo de ecepciones, necesariamente esta en la firma del metodo
     * este metodo sirve para poder hacer encriptacion de contraseña
     * @param for este ciclo for lo que hace es convertir a caracteres exadecimales por cada vuelta y en cada caracter del array
     * @param throw devuelve una excepcion si sale algo mal
     */
    public static String encryptMD5(String txt) throws Exception {
        try {
            StringBuffer sb;
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));  
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw ex;
        }
    }
    
    
}
