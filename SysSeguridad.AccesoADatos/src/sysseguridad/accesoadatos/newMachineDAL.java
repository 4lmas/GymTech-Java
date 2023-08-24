package sysseguridad.accesoadatos;

import sysseguridad.entidadesdenegocio.Machine;
import sysseguridad.entidadesdenegocio.Usuario;
import sysseguridad.accesoadatos.ComunDB;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Not4l
 */
public class newMachineDAL {

    static String getCampos() {
        return "m.Id_Machines, m.Machines_Name, m.Brand, m.Serial_Number, m.Acquisition_Date, m.Maintenance_Date, m.Next_Maintenance_Date, m.Status, m.Id";//, Id
    }

    private static String getSelect(Machine pMachine) {
        String sql;
        sql = "SELECT ";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pMachine.getTopAux() + " ";
        }
        sql += (getCampos() + " FROM Machine m");
        return sql;
    }

    private static String addOrdererBy(Machine pMachine) {
        String sql = " ORDER BY m.Id_Machines DESC";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pMachine.getTopAux() + " ";
        }
        return sql;
    }

    private static boolean existMachine(Machine pMachine) throws Exception {
        boolean exist = false;
        ArrayList<Machine> machines = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pMachine);
            sql += " WHERE m.Id_Machines<>? AND m.Status";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql)) {
                ps.setInt(1, pMachine.getIdMachines());
                ps.setByte(2, pMachine.getStatus());
                getData(ps, machines);
                ps.close();
            } catch (SQLException dataIsNull) {
                throw dataIsNull;
                
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        if (machines.size() > 0) {
            Machine machine;
            machine = machines.get(0);
            if (machine.getIdMachines() > 0) {
                exist = true;
            }
        }
        if(machines.size() > 0){
            System.out.println("tiene algo" + machines.size());
        } else {
            System.out.println("no hay nada + " + machines.size());
        }
        return exist;
    }

    //de ser necesario agregar id y cambiar valores de status a byte y datos a Date()
    public static int create(Machine pMachine) throws Exception {
        int result;
        String sql;
        boolean exist = existMachine(pMachine);

        if (exist == false) {
            try (Connection connect = ComunDB.obtenerConexion()) {
                sql = "INSERT INTO Machine(Machines_Name, Brand, Serial_Number, Estatus, Acquisition_Date, Maintenance_Date, Next_Maintenance_Date,Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)) {
                    ps.setString(1, pMachine.getMachinesName());
                    ps.setString(2, pMachine.getBrand());
                    ps.setString(3, pMachine.getSerialNumber());
                    ps.setByte(4, pMachine.getStatus()); // cambiar a byte si es necesario;
                    ps.setDate(5, java.sql.Date.valueOf(pMachine.getAcquisitionDate()));
                    ps.setDate(6, java.sql.Date.valueOf(pMachine.getMaintenanceDate()));
                    ps.setDate(7, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate()));
                    ps.setInt(8, pMachine.getIdUsuario());
                    result = ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        } else {
            result = 0;
            throw new RuntimeException("La maquinaria ya existe");
        }
        return result;
    }

    public static int modify(Machine pMachine) throws Exception {
        int result;
        String sql;
        boolean exist = existMachine(pMachine);
        if (exist == false) {
            try (Connection conn = ComunDB.obtenerConexion();) {
                sql = "UPDATE Machine(Machines_Name,Brand,Serial_Number,Status,Acquisition_Date,Maintenance_Date,Next_Maintenance_Date,Id) VALUES(?,?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                    ps.setString(1, pMachine.getMachinesName());
                    ps.setString(2, pMachine.getBrand());
                    ps.setString(3, pMachine.getSerialNumber());
                    ps.setByte(4, pMachine.getStatus());
                    ps.setString(5, pMachine.getAcquisitionDate());
                    ps.setString(6, pMachine.getMaintenanceDate());
                    ps.setString(7, pMachine.getNextMaintenanceDate());
                    ps.setInt(8, pMachine.getIdUsuario());
                    result = ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    throw ex;
                }
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } else {
            result = 0;
            throw new RuntimeException("La maquinaria ya existe");
        }
        return result;
    }

    public static int delete(Machine pMachine) throws Exception {
        int result;
        String sql;
        try (Connection connect = ComunDB.obtenerConexion()) {
            sql = "DELETE FROM Machine WHERE Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)) {
                ps.setInt(1, pMachine.getIdMachines());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            connect.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    //"m.Id_Machines, m.Machines_Name, m.Brand, m.Serial_Number, m.Acquisition_Date, m.Maintenance_Date, m.Next_Maintenance_Date, Estatus, Id";
    static int asignDataOfResultSet(Machine pMachine, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pMachine.setIdMachines(pResultSet.getInt(pIndex));
        pIndex++;
        pMachine.setMachinesName(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setBrand(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setSerialNumber(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setAcquisitionDate(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setMaintenanceDate(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setNextMaintenanceDate(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setStatus(pResultSet.getByte(pIndex));
        pIndex++;
        pMachine.setIdUsuario(pResultSet.getInt(pIndex));
        //pIndex++;
        //pMachine.setIdUsuario(pResultSet.getInt(pIndex));
        // maybe agregar quien le da mantenimiento
        return pIndex;
    }

    private static void getData(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)) {
            while (resultSet.next()) {
                Machine machine = new Machine();

                asignDataOfResultSet(machine, resultSet, 0);
                pMachines.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    // metodo que ejecuta el result set y se pueden obtener los usuarios para esto
    // EN DADO CASO DE HACERLO FUNCIONAR CREARE UNA NUEVA TABLA QUE CONTENGA SOLO LOS ENCARGADOS DE MANTENIMIENTOD DE MAQUINARIAS
    // AGREGAR EN EL METODO DE CREATE EL USUARIO ENCARGADO DE REALIZAR EL MANTENIMIETNO A MAQUINARIA
    private static void getDataIncludesUser(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)) {
            while (resultSet.next()) {
                Machine machine = new Machine();
                int index = asignDataOfResultSet(machine, resultSet, 0);
                pMachines.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    //obtener id desde la tabla de maquinaria
    public static Machine getById(Machine pMachine) throws Exception {
        Machine machine = new Machine();
        ArrayList<Machine> machines = new ArrayList();
        try (Connection connect = ComunDB.obtenerConexion()) {
            String sql = getSelect(pMachine);
            sql += " WHERE m.Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)) {
                ps.setInt(1, pMachine.getIdMachines());
                getData(ps, machines);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            connect.close();
        } catch (SQLException ex) {
            throw ex;
        }
        if (machines.size() > 0) {
            machine = machines.get(0);
        }
        return machine;
    }

    // en dado caso ser necesario agregrar el metodo getById
    // Y TAMBIEN EL GETALL de usuario ya ue raliza casi lo mismo que el codigo anterior
    //AGREGAR LO QUE HACE FALTA incluidos el buscador;
    // POR EL MOMENTO VEO INNECESARIOS LOS OTROS METODOS
    //BASADO EN USUARIO
    public static ArrayList<Machine> getAll() throws Exception {
        ArrayList<Machine> machines;
        machines = new ArrayList<>();
        try (Connection connect = ComunDB.obtenerConexion()) {
            String sql = getSelect(new Machine());
            sql += addOrdererBy(new Machine());
            try (PreparedStatement statement = ComunDB.createPreparedStatement(connect, sql)) {
                getData(statement, machines);
                statement.close();
            } catch (SQLException ex) {
                throw ex;
            }
            connect.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return machines;
    }

    static void querySelect(Machine pMachine, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        // en dado caso que no queremos que salga el id en la lista entonces simplemente comentamos esta parte
        // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getIdMachines() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Id_Machines=?");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdMachines());
            }
        }

        // verificar si se va incluir el campo Machines_Name en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getMachinesName() != null && pMachine.getMachinesName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd("m.Machines_Name LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getMachinesName() + "%");
            }
        }

        // Verificar si se va incluir el campo Brand en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getBrand() != null && pMachine.getBrand().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd("m.Brand LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getBrand() + "%");
            }
        }

        // agregar el parametro del campo Serial_Number a la consulta SELECT de la tabla de Machines
        if (pMachine.getSerialNumber() != null && pMachine.getSerialNumber().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Serial_Number=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getSerialNumber());
            }
        }

        // Verificar si se va incluir el campo Acquisition_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getAcquisitionDate() != null && pMachine.getAcquisitionDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Acquisition_Date=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getAcquisitionDate());
            }
        }

        // Verificar si se va incluir el campo Maintenance_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getMaintenanceDate() != null && pMachine.getMaintenanceDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Maintenance_Date=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getMaintenanceDate());
            }
        }

        // Verificar si se va incluir el campo Next_Maintenance_Date en el filtro de la consulta SELECT de la tabla de Machines
        if (pMachine.getNextMaintenanceDate() != null && pMachine.getNextMaintenanceDate().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" m.Next_Maintenance_Date=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMachine.getNextMaintenanceDate());
            }
        }

        if (pMachine.getStatus() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Status=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getStatus());
            }
        }
    }
    
    /**
 * Busca máquinas incluyendo información de usuarios.
 *
 * @param pMachine La máquina a buscar.
 * @return Una lista de objetos Machine que coinciden con la búsqueda.
 * @throws Exception Si ocurre algún error durante la búsqueda.
 */
    public static ArrayList<Machine> searchIncludeUser(Machine pMachine) throws Exception {
        ArrayList<Machine> machines = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion()){
            String sql = "SELECT ";
            if(pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER){
                sql += "TOP " + pMachine.getTopAux() + " ";
            }
            sql += getCampos();
            sql += ",";
            sql += UsuarioDAL.obtenerCampos();
            sql += " FROM Machine m";
            sql += " JOIN Usuario u ON (m.Id = u.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMachine, utilQuery);
            sql = utilQuery.getSQL();
            sql += addOrdererBy(pMachine);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);){
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMachine, utilQuery);
                getDataIncludesUser(ps, machines);
                ps.close();
            } catch (SQLException ex){
                throw ex;
            }
            conn.close();
        } catch (SQLException ex){
            throw ex;
        }
        return machines;
    }
}
