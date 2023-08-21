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

        return "m.Id_Machines, m.IdUsuario, m.Machines_Name, m.Brand, m.Serial_Number, m.Acquisition_Date, m.Maintenance_Date, m.Next_Maintenance_Date";
    }

    private static String getSelect(Machine pMachine) {
        String sql;
        sql = "SELECT";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pMachine.getTopAux() + "";
        }
        sql += (getCampos() + "FROM Machine m");
        return sql;
    }

    private static String addOrdererBy(Machine pMachine) {
        String sql = "ORDER BY m.Id_Machines DESC";
        if (pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pMachine.getTopAux() + " ";
        }
        return sql;
    }

    private static boolean existMachine(Machine pMachine) throws Exception {
        boolean exist = false;
        ArrayList<Machine> machines = new ArrayList();
        try (Connection connect = ComunDB.obtenerConexion()) {
            String sql = getSelect(pMachine);
            sql += " WHERE m.Id_Machines <> ? AND m.Status = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)) {
                ps.setInt(1, pMachine.getIdMachines());
                ps.setByte(2, pMachine.getEstatus());
                getData(ps, machines);  // aqui va el metodo que aun no existe, es importante que le ponga este nombre
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            connect.close();
        } catch (SQLException ex) {
            throw ex;
        }

        if (machines.size() > 0) {
            Machine machine;
            machine = machines.get(0);
            if (machine.getIdMachines() > 0 && machine.getEstatus() == pMachine.getEstatus()) {
                exist = true;
            }
        }
        return exist;
    }
    
    //AGREGANDO LA LISTA DE USUARIOS QUE TRAERE PARA POSTERIORMENTE AGREGAR LOS SELECCIONADOS A A LA CREACION DE MAQUINARIA
    /////
    ////
    
    //de ser necesario agregar id y cambiar valores de status a byte y datos a Date()
    public static int create(Machine pMachine) throws Exception {
        int result;
        String sql;
        boolean exist = existMachine(pMachine);

        if (exist == false) {
            try (Connection connect = ComunDB.obtenerConexion()) {
                sql = "INSERT INTO Machine (Machines_Name, Brand, Serial_Number, Estatus, Acquisition_Date, Maintenance_Date, Next_Maintenance_Date) VALUES (?, ?, ?, ?, ?, ?, ?);";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)) {
                    ps.setString(1, pMachine.getMachinesName());
                    ps.setString(2, pMachine.getBrand());
                    ps.setInt(3, pMachine.getSerialNumber());
                    ps.setByte(4, pMachine.getEstatus()); // cambiar a byte si es necesario;
                    ps.setDate(5, java.sql.Date.valueOf(pMachine.getAcquisitionDate()));
                    ps.setDate(6, java.sql.Date.valueOf(pMachine.getMaintenanceDate()));
                    ps.setDate(7, java.sql.Date.valueOf(pMachine.getNextMaintenanceDate()));
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
                sql = "UPDATE Machines(Machines_Name,Brand,Serial_Number,Status,Acquisition_Date,Maintenance_Date,Next_Maintenance_Date) VALUES(?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                    ps.setString(1, pMachine.getMachinesName());
                    ps.setString(2, pMachine.getBrand());
                    ps.setInt(3, pMachine.getSerialNumber());
                    ps.setByte(4, pMachine.getEstatus());
                    ps.setString(5, pMachine.getAcquisitionDate());
                    ps.setString(6, pMachine.getMaintenanceDate());
                    ps.setString(7, pMachine.getNextMaintenanceDate());
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
        try (Connection connect = ComunDB.obtenerConexion()){
            sql = "DELETE FROM Machine WHERE Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(connect, sql)){
                ps.setInt(1, pMachine.getIdMachines());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex){
                throw ex;
            }
            connect.close();
        } catch (SQLException ex){
            throw ex;
        }
        return result;
    }
    
    static int asignDataOfResultSet(Machine pMachine, ResultSet pResultSet, int pIndex) throws Exception {
        // Descomentar en caso de ser necesario ya que el id es autoincrementable
        // pIndex++;
        //pMachine.setIdMachines(pResultSet.getInt(pIndex));
        pIndex++;
        pMachine.setMachinesName(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setBrand(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setSerialNumber(pResultSet.getInt(pIndex));
        pIndex++;
        pMachine.setAcquisitionDate(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setMaintenanceDate(pResultSet.getString(pIndex));
        pIndex++;
        pMachine.setNextMaintenanceDate(pResultSet.getString(pIndex));
        // maybe agregar quien le da mantenimiento
        return pIndex;
    }

    private static void getData(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)){
            while (resultSet.next()){
                Machine machine = new Machine();
                asignDataOfResultSet(machine, resultSet, 0);
                pMachines.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex){
            throw ex;
        }
    }
    
    // metodo que ejecuta el result set y se pueden obtener los usuarios para esto
    // EN DADO CASO DE HACERLO FUNCIONAR CREARE UNA NUEVA TABLA QUE CONTENGA SOLO LOS ENCARGADOS DE MANTENIMIENTOD DE MAQUINARIAS
    // AGREGAR EN EL METODO DE CREATE EL USUARIO ENCARGADO DE REALIZAR EL MANTENIMIETNO A MAQUINARIA
    
    private static void getDataIncludesUser(PreparedStatement pPS, ArrayList<Machine> pMachines) throws Exception{
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)){
            while (resultSet.next()){
                Machine machine = new Machine();
                int index = asignDataOfResultSet(machine, resultSet, 0);
                pMachines.add(machine);
            }
            resultSet.close();
        } catch (SQLException ex){
            throw ex;
        }
    }
    
    //obtener id desde la tabla de maquinaria
    
    public static Machine getById(Machine pMachine) throws Exception{
        Machine machine = new Machine();
        ArrayList<Machine> machines = new ArrayList();
        try (Connection connect = ComunDB.obtenerConexion()){
            String sql = getSelect(pMachine);
            sql += " WHERE m.Id_Machines=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(connect,sql)){
               ps.setInt(1, pMachine.getIdMachines());
               getData(ps, machines);
               ps.close();
            } catch (SQLException ex){
                throw ex;
            }
            connect.close();
        } catch (SQLException ex){
            throw ex;
        } if (machines.size() > 0){
            machine = machines.get(0);
        }
        return machine;
    }
    
    // en dado caso ser necesario agregrar el metodo getById
    // Y TAMBIEN EL GETALL de usuario ya ue raliza casi lo mismo que el codigo anterior
    //AGREGAR LO QUE HACE FALTA incluidos el buscador;
    // POR EL MOMENTO VEO INNECESARIOS LOS OTROS METODOS
    //BASADO EN USUARIO
    
    public static ArrayList<Machine> getAll()throws Exception{
        ArrayList<Machine> machines;
        machines = new ArrayList<>();
        try (Connection connect = ComunDB.obtenerConexion()){
            String sql = getSelect(new Machine());
            sql += addOrdererBy(new Machine());
            try (PreparedStatement statement = ComunDB.createPreparedStatement(connect, sql)){
                getData(statement, machines);
                statement.close();
            } catch (SQLException ex){
                throw ex;
            } connect.close();
        } catch (SQLException ex){
            throw ex;
        }
        return machines;
    }
    
    
    static void querySelect(Machine pMachine, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        // en dado caso que no queremos que salga el id en la lista entonces simplemente comentamos esta parte
        // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getIdMachines() > 0){
            pUtilQuery.AgregarWhereAnd(" m.Id_Machines=?");
            if (statement != null){
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdMachines());
            }
        }
        
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pMachine.getIdUsuario() > 0){
            pUtilQuery.AgregarWhereAnd(" m.IdUsuario=?");
            if(statement != null){
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getIdUsuario());
                
            }
        }
        
        // verificar si se va incluir el campo Machines_Name en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getMachinesName() != null && pMachine.getMachinesName().trim().isEmpty() == false){
            pUtilQuery.AgregarWhereAnd("m.Machines_Name LIKE ? ");
            if (statement != null){
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getMachinesName() + "%");
            }
        }
        
        // Verificar si se va incluir el campo Brand en el filtro de la consulta SELECT de la tabla de Machine
        if (pMachine.getBrand() != null && pMachine.getBrand().trim().isEmpty() == false){
            pUtilQuery.AgregarWhereAnd("m.Brand LIKE ? ");
            if (statement != null){
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMachine.getBrand() + "%");
            }
        }
        
        // agregar el parametro del campo Serial_Number a la consulta SELECT de la tabla de Machines
        if (pMachine.getSerialNumber() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Serial_Number=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getSerialNumber());
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
        
        if (pMachine.getEstatus() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Estatus=? "); 
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMachine.getEstatus());
            }
        }
    }
    
    
    public static ArrayList<Machine> searchIncludeUsuario(Machine pMachine) throws Exception {
        ArrayList<Machine> machines = new ArrayList();
        try (Connection connect = ComunDB.obtenerConexion()){
            String sql = "SELECT ";
            if(pMachine.getTopAux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER){
                sql += "TOP " + pMachine.getTopAux() + " ";
            }
            sql += getCampos();
            sql += ",";
            sql += UsuarioDAL.obtenerCampos();
            sql += " FROM Machine m";
            sql += " JOIN Usuario u ON (m.IdUsuario = u.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null,0);
            querySelect(pMachine, utilQuery);
            sql = utilQuery.getSQL();
            sql += addOrdererBy(pMachine);
            try (PreparedStatement statement = ComunDB.createPreparedStatement(connect, sql)){
                utilQuery.setStatement(statement);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMachine, utilQuery);
                //ojo
                getData(statement, machines);
                getDataIncludesUser(statement, machines);
                statement.close();
            } catch (SQLException ex){
                throw ex;
            }
            connect.close();
        }
        return machines;
    }
}