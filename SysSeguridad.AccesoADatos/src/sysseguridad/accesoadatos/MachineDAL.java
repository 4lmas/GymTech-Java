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

    static String obtenerCampos() {
        return "m.Id_Machines, m.Id, m.Machines_Name, m.Brand, m.Serial_Number, m.Acquisition_Date, m.Maintenance_Date, m.Next_Maintenance_Date";
    }

    private static String obtenerSelect(Machine pMachine) {
        String sql;
        sql = "SELECT ";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pMachine.getTopAux() + " ";
        }
        sql += (obtenerCampos() + " FROM Machine m");
        return sql;
    }

    private static String agregarOrderBy(Machine pMachine) {
        String sql = " ORDER BY m.Id_Machines DESC";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pMachine.getTopAux() + " ";
        }
        return sql;
    }

    // Metodo para poder insertar un nuevo registro en la tabla de Machine
    public static int crear(Machine pMachine) throws Exception {
        int result;
        String sql;

        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Machines(Id,Machines_Name,Brand,Serial_Number,Estatus,Acquisition_Date,Maintenance_Date,Next_Maintenance_Date) VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pMachine.getIdMachines());
                ps.setString(2, pMachine.getMachinesName());
                ps.setString(3, pMachine.getBrand());
                ps.setString(4, pMachine.getSerialNumber());
                ps.setByte(5, pMachine.getStatus());
                ps.setDate(6, java.sql.Date.valueOf(pMachine.getAcquisitionDate()));
                ps.setDate(7, java.sql.Date.valueOf(pMachine.getMaintenanceDate()));
                ps.setDate(8, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate()));
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    // Metodo para poder actualizar un registro en la tabla de Machines
    public static int modify(Machine pMachine) throws Exception {
        int result;
        String sql;

        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Machines SET Id=?, Machines_Name=?, Brand=?, Serial_Number=?, Estatus=?, Acquisition_Date=?, Maintenance_Date=?, Next_Maintenance_Date=? WHERE Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pMachine.getIdMachines());
                ps.setString(2, pMachine.getMachinesName());
                ps.setString(3, pMachine.getBrand());
                ps.setString(4, pMachine.getSerialNumber());
                ps.setByte(5, pMachine.getStatus());
                ps.setDate(6, java.sql.Date.valueOf(pMachine.getMaintenanceDate()));
                ps.setDate(7, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate()));
                ps.setInt(8, pMachine.getIdMachines());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int delete(Machine pMachine) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Machines WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pMachine.getIdMachines());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    // Metodo para llenar las propiedades de la entidad de Machines con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Machine pMachine, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pMachine.setIdMachines(pResultSet.getInt(pIndex));
        pIndex++;
        pMachine.setIdMachines(pResultSet.getInt(pIndex));
        pIndex++;
        pMachine.setMachinesName(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setBrand(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setSerialNumber(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setStatus(pResultSet.getByte(pIndex));
        pIndex++;
        pMachine.setAcquisitionDate(pResultSet.getString(pIndex));
        pMachine.setMaintenanceDate(pResultSet.getString(pIndex));
        pMachine.setNextMaintenanceDate(pResultSet.getString(pIndex));
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Machine machine = new Machine();
                asignarDatosResultSet(machine, resultSet, 0);
                pMachines.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    
    /// ESTO LOGICAMENTE ES AL REVEZ, HAY QUE CAMBIAR LA LISTA
    

    //---- POSIBLES CONFLICTOS ----//
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Machines y JOIN a la tabla de Usuario
    private static void obtenerDatosIncluirUsuario(PreparedStatement pPS, ArrayList<Machine> pMachine) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            HashMap<Integer, Usuario> usuarioMap = new HashMap();
            while (resultSet.next()) {
                Machine machine = new Machine();

                int index = asignarDatosResultSet(machine, resultSet, 0);
                if (usuarioMap.containsKey(machine.getIdMachines()) == false) {
                    Usuario usuario = new Usuario();

                    UsuarioDAL.asignarDatosResultSet(usuario, resultSet, index);
                    usuarioMap.put(usuario.getId(), usuario);
                    
                } else {
                    Usuario usuario = new Usuario();

                    String keyUserName = usuario.getNombre();
                    
                }
                pMachine.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

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
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return machines; // devolver el ArrayList de Machine
    }

    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Machines de forma dinamica
    static void querySelect(Machine pMachine, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pMachine.getIdMachines() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Machines
            pUtilQuery.AgregarWhereAnd(" m.Id_Machines=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                // agregar el parametro del campo Id a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdMachines());
            }
        }
        // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
        if (pMachine.getIdMachines() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // agregar el parametro del campo Id a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdMachines());
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
        if (pMachine.getSerialNumber() != null && pMachine.getSerialNumber().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Serial_Number=? "); // agregar el campo Serial_Number al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // agregar el parametro del campo Serial_Number a la consulta SELECT de la tabla de Machines
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getSerialNumber());
            }
        }
        // Verificar si se va incluir el campo Estatus en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getStatus() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Estatus=? "); // agregar el campo Estatus al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // agregar el parametro del campo Estatus a la consulta SELECT de la tabla de Machines
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getStatus());
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
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pMachine); 
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMachine, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pMachine);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMachine, utilQuery); 
                obtenerDatos(ps, machines);
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
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
            if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pMachine.getTopAux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
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
