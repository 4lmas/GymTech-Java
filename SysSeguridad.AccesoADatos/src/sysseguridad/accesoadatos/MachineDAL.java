package sysseguridad.accesoadatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import sysseguridad.entidadesdenegocio.Usuario;
import sysseguridad.entidadesdenegocio.Machine;

//probando subir
public class MachineDAL { // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Machine

    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Machine
    static String obtenerCampos() {
        return "m.Id_Machines, m.Id, m.Machines_Name, m.Brand, m.Serial_Number, m.Acquisition_Date, m.Maintenance_Date, m.Next_Maintenance_Date";
    }
    // Metodo para obtener el SELECT a la tabla Machine y el top en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Machine pMachine) {
        String sql;
        sql = "SELECT ";
        if (pMachine.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
             // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y getTop_aux es mayor a cero
            sql += "TOP " + pMachine.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Machines m");
        return sql;
    }   
    // Metodo para obtener Order by a la consulta SELECT de la tabla Machine y ordene los registros de mayor a menor 
    private static String agregarOrderBy(Machine pMachine) {
        String sql = " ORDER BY m.Id_Machines DESC";
        if (pMachine.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Machine en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pMachine.getTop_aux() + " ";
        }
        return sql;
    }

 
    
    // Metodo para poder insertar un nuevo registro en la tabla de Machine
    public static int crear(Machine pMachine) throws Exception {
        int result;
        String sql;
        
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                 //Definir la consulta INSERT a la tabla de Machine utilizando el simbolo "?" para enviar parametros
                sql = "INSERT INTO Machines(Id,Machines_Name,Brand,Serial_Number,Estatus,Acquisition_Date,Maintenance_Date,Next_Maintenance_Date) VALUES(?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pMachine.getId()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                    ps.setString(2, pMachine.getMachinesName()); // Agregar el parametro a la consulta donde estan el simbolo "?" #2 
                    ps.setString(3, pMachine.getBrand()); // agregar el parametro a la consulta donde estan el simbolo "?" #3 
                    ps.setInt(4, pMachine.getSerialNumber()); // agregar el parametro a la consulta donde estan el simbolo "?" #4 
                    ps.setByte(5, pMachine.getEstatus()); // agregar el parametro a la consulta donde estan el simbolo "?" #5 
                    ps.setDate(6, java.sql.Date.valueOf(pMachine.getAcquisitionDate())); // agregar el parametro a la consulta donde estan el simbolo "?" #6 
                    ps.setDate(7, java.sql.Date.valueOf(pMachine.getMaintenanceDate())); // agregar el parametro a la consulta donde estan el simbolo "?" #7 
                    ps.setDate(8, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate())); // agregar el parametro a la consulta donde estan el simbolo "?" #8
                    result = ps.executeUpdate(); // ejecutar la consulta INSERT en la base de datos
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
                }
                conn.close();
            } // Handle any errors that may have occurred.
            catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al obtener la conexion en el caso que suceda
            }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }

     // Metodo para poder actualizar un registro en la tabla de Machines
    public static int modify(Machine pMachine) throws Exception {
        int result;
        String sql;
   
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                //Definir la consulta UPDATE a la tabla de Machines utilizando el simbolo ? para enviar parametros
                sql = "UPDATE Machines SET Id=?, Machines_Name=?, Brand=?, Serial_Number=?, Estatus=?, Acquisition_Date=?, Maintenance_Date=?, Next_Maintenance_Date=? WHERE Id_Machines=?";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pMachine.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #1  
                    ps.setString(2, pMachine.getMachinesName()); // agregar el parametro a la consulta donde estan el simbolo ? #2  
                    ps.setString(3, pMachine.getBrand()); // agregar el parametro a la consulta donde estan el simbolo ? #3  
                    ps.setInt(4, pMachine.getSerialNumber()); // agregar el parametro a la consulta donde estan el simbolo ? #4  
                    ps.setByte(5, pMachine.getEstatus()); // agregar el parametro a la consulta donde estan el simbolo ? #5 
                    ps.setDate(6, java.sql.Date.valueOf(pMachine.getMaintenanceDate())); // agregar el parametro a la consulta donde estan el simbolo "?" #6
                    ps.setDate(7, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate())); // agregar el parametro a la consulta donde estan el simbolo "?" #7
                    ps.setInt(8, pMachine.getIdMachines()); // agregar el parametro a la consulta donde estan el simbolo ? #8  
                    result = ps.executeUpdate(); // ejecutar la consulta UPDATE en la base de datos
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
                }
                conn.close(); // cerrar la conexion a la base de datos
            } 
            catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
            }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }

    // Metodo para poder eliminar un registro en la tabla de Usuario
    public static int delete(Machine pMachine) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Machines WHERE Id=?"; //definir la consulta DELETE a la tabla de Machines utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {  // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pMachine.getId()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate(); // ejecutar la consulta DELETE en la base de datos
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex;  // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    }

    // Metodo para llenar las propiedades de la entidad de Machines con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Machine pMachine, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pMachine.setIdMachines(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pMachine.setId(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pMachine.setMachinesName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pMachine.setBrand(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pMachine.setSerialNumber(pResultSet.getInt(pIndex)); // index 5
        pIndex++;
        pMachine.setEstatus(pResultSet.getByte(pIndex)); // index 6
        pIndex++;
        pMachine.setAcquisitionDate(pResultSet.getString(pIndex)); // index 7
        pMachine.setMaintenanceDate(pResultSet.getString(pIndex)); // index 8
        pMachine.setNextMaintenanceDate(pResultSet.getString(pIndex)); // index 9
        return pIndex;
    }

    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Machines
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Machines
                Machine machine = new Machine();
                // Llenar las propiedaddes de la Entidad Machine con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(machine, resultSet, 0);
                pMachines.add(machine); // agregar la entidad Machine al ArrayList de Machine
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    //---- POSIBLES CONFLICTOS ----//
    
    
 // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Machines y JOIN a la tabla de Usuario
    private static void obtenerDatosIncluirUsuario(PreparedStatement pPS, ArrayList<Machine> pMachine) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Usuario> usuarioMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Usuario
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Machine JOIN a la tabla de Usuario
                Machine machine = new Machine();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(machine, resultSet, 0);
                if (usuarioMap.containsKey(machine.getId()) == false) { // verificar que el HashMap aun no contenga el Usuario actual
                    Usuario usuario = new Usuario();
                    // en el caso que el Usuario no este en el HashMap se asignara
                    UsuarioDAL.asignarDatosResultSet(usuario, resultSet, index);
                    usuarioMap.put(usuario.getId(), usuario);// agregar el Usuario al  HashMap
                    machine.setNameUser(usuario.getNombre()); // agregar el Usuario a la maquina
                } else {
                    Usuario usuario = new Usuario();
                    // En el caso que el Usuario existe en el HashMap se agregara automaticamente a la maquina
                    String keyUserName = usuario.getNombre();
                    machine.setNameUser(keyUserName); 
                }
                pMachine.add(machine); // Agregar la machine de la fila actual al ArrayList de Machine
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

    // Metodo para obtener por Id un registro de la tabla de Machines 
    public static Machine getById(Machine pMachine) throws Exception {
        Machine machine = new Machine();
        ArrayList<Machine> machines = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pMachine); // obtener la consulta SELECT de la tabla Usuario
             // Concatenar a la consulta SELECT de la tabla Machines el WHERE  para comparar el campo Id
            sql += " WHERE m.Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pMachine.getIdMachines()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, machines); // Llenar el ArrayList de Machine con las fila que devolvera la consulta SELECT a la tabla de Machines
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (machines.size() > 0) { // verificar si el ArrayList de Machine trae mas de un registro en tal caso solo debe de traer uno
            machine = machines.get(0); // Si el ArrayList de Machine trae un registro o mas obtenemos solo el primero
        }
        return machine; // devolver la machine encontrado por Id 
    }

     // Metodo para obtener todos los registro de la tabla de Machines
    public static ArrayList<Machine> obtenerTodos() throws Exception {
        ArrayList<Machine> machines;
        machines = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Machine()); // obtener la consulta SELECT de la tabla Machines
            sql += agregarOrderBy(new Machine()); // concatenar a la consulta SELECT de la tabla Machines el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, machines); // Llenar el ArrayList de Machine con las fila que devolvera la consulta SELECT a la tabla de Machines
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return machines; // devolver el ArrayList de Machine
    }

    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Machines de forma dinamica
    static void querySelect(Machine pMachine, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pMachine.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Machines
            pUtilQuery.AgregarWhereAnd(" m.Id_Machines=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdMachines());
            }
        }
        // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
        if (pMachine.getId() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getId());
            }
        }
        // verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getMachinesName() != null && pMachine.getMachinesName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Machines_Name LIKE ? "); // agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getMachinesName() + "%");
            }
        }
        // Verificar si se va incluir el campo Marca en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getBrand() != null && pMachine.getBrand().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Brand LIKE ? "); // agregar el campo Marca al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Marca a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getBrand() + "%");
            }
        }
        // Verificar si se va incluir el campo Serial_Number en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getSerialNumber() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Serial_Number=? "); // agregar el campo Serial_Number al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Serial_Number a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getSerialNumber());
            }
        }
        // Verificar si se va incluir el campo Estatus en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getEstatus() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Estatus=? "); // agregar el campo Estatus al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Estatus a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getEstatus());
            }
        }
        // Verificar si se va incluir el campo Acquisition_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getAcquisitionDate() != null && pMachine.getAcquisitionDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Acquisition_Date=? "); // agregar el campo Acquisition_Date al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Acquisition_Date a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getAcquisitionDate());
            }
        }
        // Verificar si se va incluir el campo Maintenance_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getMaintenanceDate() != null && pMachine.getMaintenanceDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Maintenance_Date=? "); // agregar el campo Maintenance_Date al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Maintenance_Date a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getMaintenanceDate());
            }
        }
        // Verificar si se va incluir el campo Next_Maintenance_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getNextMaintenanceDate() != null && pMachine.getNextMaintenanceDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Next_Maintenance_Date=? "); // agregar el campo Next_Maintenance_Date al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Next_Maintenance_Date a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getNextMaintenanceDate());
            }
        }
    }

     // Metodo para obtener todos los registro de la tabla de Machines que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Machines 
    public static ArrayList<Machine> buscarMachine(Machine pMachine) throws Exception {
        ArrayList<Machine> machines = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pMachine); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMachine, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pMachine); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMachine, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, machines); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return machines; // Devolver el ArrayList de Usuario
    }
    // Metodo para obtener todos los registro de la tabla de Usuario que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Machines 
    public static ArrayList<Machine> buscarIncluirUsuario(Machine pMachine) throws Exception {
        ArrayList<Machine> machines = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pMachine.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pMachine.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += UsuarioDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Machines m";
            sql += " JOIN Usuario u on (m.Id=u.Id_Machines)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMachine, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pMachine); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMachine, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirUsuario(ps, machines);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return machines; 
    }

}