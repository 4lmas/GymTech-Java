package sysseguridad.entidadesdenegocio;

public class Machine {

    private int Id_Machines;
    private int IdUsuario;
    private String Machines_Name;
    private String Brand;
    private String Serial_Number;
    private String Acquisition_Date;
    private String Maintenance_Date;
    private String Next_Maintenance_Date;
    private byte Status;
    private int Top_Aux;
    private Usuario usuario;

    public Machine(int id_Machines, int idUsuario, String machines_Name, String brand, String serial_Number,
            String acquisition_Date, String maintenance_Date, String next_Maintenance_Date,
            byte Status, int top_Aux) {
        this.Id_Machines = id_Machines;
        this.IdUsuario = idUsuario;
        this.Machines_Name = machines_Name;
        this.Brand = brand;
        this.Serial_Number = serial_Number;
        this.Acquisition_Date = acquisition_Date;
        this.Maintenance_Date = maintenance_Date;
        this.Next_Maintenance_Date = next_Maintenance_Date;
        this.Status = Status;
        this.Top_Aux = top_Aux;
    }

    public Machine() {

    }

    public int getIdMachines() {
        return Id_Machines;
    }

    public void setIdMachines(int id_Machines) {
        Id_Machines = id_Machines;
    }

    public void setIdUsuario(int IdUsuario){
        this.IdUsuario =IdUsuario;
    }
    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMachinesName() {
        return Machines_Name;
    }

    public void setMachinesName(String machines_Name) {
        Machines_Name = machines_Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getSerialNumber() {
        return Serial_Number;
    }

    public void setSerialNumber(String serial_Number) {
        Serial_Number = serial_Number;
    }

    public String getAcquisitionDate() {
        return Acquisition_Date;
    }

    public void setAcquisitionDate(String acquisition_Date) {
        Acquisition_Date = acquisition_Date;
    }

    public String getMaintenanceDate() {
        return Maintenance_Date;
    }

    public void setMaintenanceDate(String maintenance_Date) {
        Maintenance_Date = maintenance_Date;
    }

    public String getNextMaintenanceDate() {
        return Next_Maintenance_Date;
    }

    public void setNextMaintenanceDate(String next_Maintenance_Date) {
        Next_Maintenance_Date = next_Maintenance_Date;
    }

    public byte getStatus() {
        return Status;
    }

    public void setStatus(byte Status) {
        Status = Status;
    }

    public int getTopAux() {
        return Top_Aux;
    }

    public void setTopAux(int top_Aux) {
        Top_Aux = top_Aux;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    public void set(Usuario usuario){
        this.usuario = usuario;
    }

    public class statusMachine {

        public static final byte ACTIVO = 1;
        public static final byte INACTIVO = 2;
    }
}
