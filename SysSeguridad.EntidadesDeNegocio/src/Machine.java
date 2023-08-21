public class Machine {
    private int Id_Machines;
    private int IdUsuario;
    private String Machines_Name;
    private String Brand;
    private int Serial_Number;
    private String Acquisition_Date;
    private String Maintenance_Date;
    private String Next_Maintenance_Date;
    private byte Estatus;
    private int Top_Aux;

    public Machine(int id_Machines, int idUsuario, String machines_Name, String brand, int serial_Number,
                   String acquisition_Date, String maintenance_Date, String next_Maintenance_Date,
                   byte estatus, int top_Aux) {
        Id_Machines = id_Machines;
        IdUsuario = idUsuario;
        Machines_Name = machines_Name;
        Brand = brand;
        Serial_Number = serial_Number;
        Acquisition_Date = acquisition_Date;
        Maintenance_Date = maintenance_Date;
        Next_Maintenance_Date = next_Maintenance_Date;
        Estatus = estatus;
        Top_Aux = top_Aux;
    }

    public int getIdMachines() {
        return Id_Machines;
    }

    public void setIdMachines(int id_Machines) {
        Id_Machines = id_Machines;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
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

    public int getSerialNumber() {
        return Serial_Number;
    }

    public void setSerialNumber(int serial_Number) {
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

    public byte getEstatus() {
        return Estatus;
    }

    public void setEstatus(byte estatus) {
        Estatus = estatus;
    }

    public int getTopAux() {
        return Top_Aux;
    }

    public void setTopAux(int top_Aux) {
        Top_Aux = top_Aux;
    }
}
